package game;

import java.util.Scanner;

import cards.LuckyCard;
import squares.*;
import utility.DiceRoller;

public class JavopolyGame {

	// all the instance variables we need to run a game of Javopoly...

	private static JavopolyGame game;
	/**
	 * number of players in this game
	 */
	private int nPlayer;
	/**
	 * all players in the game
	 */
	private Player[] player;

	/**
	 * @return the number of players in this game
	 */
	public int getNumPlayers() {
		return nPlayer;
	}

	/**
	 * Get the Nth player in this game. Returns the actual Player object, not a
	 * copy.
	 * 
	 * @param ix
	 *            which player to return
	 * @return the player, or null if ix is invalid
	 */
	public Player getPlayer(int ix) {
		try {
			return player[ix];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * the game board
	 */
	private GameBoard board;

	/**
	 * return the actual GameBoard object (not a copy) associated with this
	 * game.
	 * 
	 * @return the GameBoard
	 */
	public GameBoard getBoard() {
		return board;
	};

	/**
	 * A deck of LuckyCards
	 */
	private CardStock chanceCards;

	/**
	 * dice
	 */
	private DiceRoller dice;
	/**
	 * the last sum rolled by the dice
	 */
	private int lastDiceRoll;
	/**
	 * descriptor of dice for Prob. 2d6 means 2 dice with 6 sides.
	 */
	private static final String DICE_TYPE = "2d6";

	////////////////////////////////////////////////////////

	// CONSTRUCTION and SETUP

	/**
	 * create the stock of LuckyCards COMMENT THE FIRST LINE AND UNCOMMENT THE
	 * SECOND LINE WHEN YOU HAVE CREATED CLASSES FOR ALL THE LUCKY CARD TYPES
	 */
	private void setupCards() {
		// chanceCards = new CardStock(12,1,true);
		chanceCards = new CardStock(12, 2, true);
	}

	/**
	 * create Players and add them to the game.
	 */
	private void setupPlayers() {
		int i = 0;
		try {
			for (i = 0; i < nPlayer; i++) {
				player[i] = new Player(this); // 15/2/18 added this parm
			}
		} catch (OutOfTokensException e) {
			System.out.println(">>" + e.getMessage() + "<<");
			System.out.println("limiting number of players to " + i);
			nPlayer = i;
		}
	}

	/**
	 * Construct a Javopoly game with npl Players. CHOOSE WHICH SETUP BY
	 * UNCOMMENTING ONE OF THE INITIALISATION LINES WITHIN
	 * 
	 * @param npl
	 */
	public JavopolyGame(int npl) {
		nPlayer = npl;
		player = new Player[nPlayer];
		setupPlayers();

		setupCards();

		dice = new DiceRoller(DICE_TYPE);
		lastDiceRoll = 0;

		// board = GameBoard.testInitialisation(0, this); // a board full of
		// Do-Nothing squares
		board = GameBoard.testInitialisation(2, this); // a board with most
														// square types
//		 board = GameBoard.mawsonLakesInitialisation( this); // a board
		// modelled on Mawson Lakes
		// EXACTLY ONE OF THESE MUST BE UNCOMMENTED.
		// ALL BUT THE FIRST REQUIRE YOU TO WRITE NEW CLASSES.
	}

	////////////////////////////////////////////////////////////

	/**
	 * roll the dice, record the result and return it
	 * 
	 * @return the value of the dice roll
	 */
	public int rollTheDice() {
		lastDiceRoll = dice.getDiceRoll(DICE_TYPE);
		return lastDiceRoll;
	}

	/**
	 * return the last value rolled by the dice (without rolling again)
	 * 
	 * @return the last-rolled dice value
	 */
	public int getLastDiceRoll() {
		return lastDiceRoll;
	}

	/**
	 * get the next LuckyCard from thr CardStock
	 * 
	 * @return the LuckyCard
	 */
	public LuckyCard getLuckyCard() {
		return chanceCards.serveCard();
	}

	/**
	 * 
	 */
	public void dumpGameToFile() {
		OutputPlayers.writePlayersToFile(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// return "THIS IS STUB JavopolyGame.toString()";
		String str = "";
		for (int i = 0; i < nPlayer; i++) {
			str += player[i].toString();
			str += "\n";
			str += player[i].listOfProperties();
			str += "\n";
			str += "--------\n";
		}
		return str;
	}

	/**
	 * @param ply
	 * @param roll
	 * @return
	 */
	public boolean movePlayer(Player ply, int roll) {
		int posIxOrg = ply.getSquareIndex();
		BoardSquare bsqp = board.getSquare(posIxOrg);
		boolean OK1 = board.getSquare(posIxOrg).handlePlayerLeaving(ply);
		if (!OK1) {
			System.out.println("Could not move.");
			return false;
		} else if (bsqp instanceof Gaol) {
			boolean hasPassed = passGaol(ply);
			if (!hasPassed) {
				System.out.println("Still in Gaol square");
				return false;
			}
		}
		int posIx = ply.advancePosition(roll);

		// did we pass Go?
		if (posIx < posIxOrg) { // and player ain't in gaol. May move this to
								// after entry handler.
			System.out.println(ply.playerID() + " has completed a circuit, gain $200.");
			ply.addToCash(200.00);
		}

		BoardSquare bsq = board.getSquare(posIx);
		if (bsq instanceof PotLuck) {
			getLuckyCard().enactCard(ply, game);
			System.out.println("Played a lucky card");
		} else if (bsq instanceof Gaol) {
			boolean hasPassed = passGaol(ply);
			if (!hasPassed) {
				System.out.println("Still in Gaol square");
				return false;
			}
		} else if (bsq instanceof Property) {
			if (((Property) bsq).isOwned()) {
				if (((Property) bsq).getOwner() == ply) {
					System.out.println(ply.playerID() + " owns " + bsq.getClass().getName() + ", " + bsq.getName());
				} else {
					Player owner = ((Property) bsq).getOwner();
					double rent = ((Property) bsq).computeRent(owner);
					ply.decreaseCash(rent);
					owner.addToCash(rent);
					System.out.println(ply.playerID() + " paid a rent of " + rent + " to " + owner.playerID());
				}
			} else if (bsq.isBuyable((Property) bsq, ply)) {
				ply.decreaseCash(((Property) bsq).cost);
				ply.addToPortfolio((Property) bsq);
				System.out.println(ply.playerID() + " has bought a property " + 
				  bsq.getClass().getName() + ", " + bsq.getName());
			}
		}

		// look where we've landed
		System.out.println("Landed on: " + board.getSquare(posIx));
		// run the entry-handler
		board.getSquare(posIx).handlePlayerEntry(ply); // , this);

		return true;
	}

	public boolean passGaol(Player ply) {
		int diceFace = rollTheDice();
		if (diceFace == 2 || diceFace == 3 || diceFace == 11 || diceFace == 12) {
			movePlayer(ply, diceFace);
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	public void playTheGame() {
		// Scanner scin = new Scanner(System.in);

		int upto = 0;
		for (;; upto = (upto + 1) % nPlayer) {

			System.out.println("\n" + player[upto]);

			if (player[upto].getCash() <= 0) {
				System.out.println(player[upto].playerID() + " is insolvent and must miss this turn.");
				continue;
			}

			boolean breakLoop, quitGame = false;
			do {
				System.out.print("type 'roll', 'dump' or 'quit': ");
				breakLoop = true;

				String input = readAWord();
				char inChar = input.toLowerCase().charAt(0);

				switch (inChar) {
				case 'q':
					quitGame = true;
					break;
				case 'd':
					dumpGameToFile();
					breakLoop = false;
					break;
				case 'r':
					// do nothing here...
					break;
				default:
					System.out.println("Huh? ");
					breakLoop = false;
				}
			} while (!breakLoop);
			if (quitGame)
				break;

			// at this point we should be rolling.

			int roll = rollTheDice();
			System.out.println("dice roll is " + roll);

			boolean OK = movePlayer(player[upto], roll);
			assert (OK);

			System.out.println(player[upto]);
		}
	}

	/**
	 * @return
	 */
	private static String readAWord() {
		Scanner scin = new Scanner(System.in);
		String input = scin.next().toLowerCase();
		scin.nextLine(); // any point if we close the scanner?
		// scin.close();
		return input;
	}
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		game = new JavopolyGame(4);

		// uncomment this to enable user-inputted dice rolls when the game
		// plays.
		// DiceRoller.setSeed(true);

		game.playTheGame();
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	//

}
