package game;

import cards.*;
import squares.*;


/**
 * 		 To enable assertions:
 *	 Run->RunConfigs, Arguments tab, in VM arguments box add "-ea"  (without the quotes)
 *
 * @author sjs
 *
 */
public class TestRoutines {

	/**
	 * Purpose: that an AdvanceToken card works, i.e. moves payer forward.
	 * How: create a JavopolyGame with 1 Player
	 * 		set the Player's position
	 * 		create an AdvanceToken card
	 * 		enact the card on the Player
	 * 		check that PLayer's position has increased by the value of the card.
	 * Success:	player has advanced by the value of the card.
	 * @return  true on success.
	 */
	public static boolean testAdvanceTokenCard(){
		// create a board with a Player for testing
		JavopolyGame jGame = new JavopolyGame(1);
		// get that Player
		Player ply = jGame.getPlayer(0);
		// set player's position
		int initPos = 30;
		ply.advancePosition(initPos);
		// create an AdvanceToken LuckyCard
		int nAdvance = 5;
		LuckyCard card = new AdvanceToken("testing advance token",nAdvance);
		// enact the card on the player
		boolean OK = card.enactCard(ply, jGame);
		if (!OK) {
			System.out.println("enactCard failed.");
			return false;
		}
		// see if player is in correct position - note positions wrap around (%BOARDSIZE).
		int newPos = ply.getSquareIndex();
		OK = newPos == (initPos+nAdvance)%GameBoard.BOARDSIZE;
		System.out.printf("initial pos %d, nAdvance %d, new pos %d:  ",initPos,nAdvance,newPos);
		System.out.println(OK);
		return OK;
	}

	/**
	 * Purpose: Test the mechanics of Street acquisition
	 * How: Create a JavopolyGame with 2 Players.
	 * 		Create a Street and set it as a square of the board.
	 * 		Enter first player onto this square
	 * 			- check that player is now owner of the Street
	 * 		Enter second player into the square
	 * 			- check both player's balances to see that rent was paid.
	 * Success: player 1 owns the street and has received rent from player 2.
	 * @return true on success.
	 */
	public static boolean testStreet(){
		// create a game with 2 Players for this test
		JavopolyGame jGame = new JavopolyGame(2);
		Player ply0 = jGame.getPlayer(0);
		Player ply1 = jGame.getPlayer(1);
		
		// create a Street for testing
		double streetPrice = 1000.00;  // this must be affordable by a new Player.
		Street bsq = new Street("Test Street", streetPrice, jGame);
		// now set this to a BoardSquare in the game, replacing anything already there.
		int squareIndex = 9;
		boolean wasSet = jGame.getBoard().setSquare(squareIndex, bsq);
		assert(wasSet);  // if this fails the test ain't gonna work!
		// requires setSquare to be public, unless this test is put in game package.
		
		// enter first player into the street and test ownership
		// (we assume that it has enough coin to buy the property)
		bsq.handlePlayerEntry(ply0);
		boolean OK = bsq.isOwned()&&(bsq.getOwner()==ply0);
		System.out.println("ownership test: " + OK);
		
		// get bank balances
		double bal0 = ply0.getCash();
		double bal1 = ply1.getCash();
		// enter second player into the street
		bsq.handlePlayerEntry(ply1);
		// rent should be 10% of price.  Check that bank balances have been changed accordingly.
		double rent = 0.1*streetPrice;
		OK = OK && (ply0.getCash()==bal0+rent) && (ply1.getCash()==bal1-rent);
		
		return OK;
	}
	
	/***  WRITE SOME MORE MEMBERS FOR TESTING HERE ***/
	
	/////////////////////////////////////////////
	
	public static void main(String[] args) {

		boolean passed;
		
		System.out.println("TEST A");
		passed = testAdvanceTokenCard();
		assert(passed);
		
		System.out.println("TEST B");
		passed = testStreet();
		assert(passed);
		
		// uncomment this to make sure assert is working.
		// Need to enable assertions: 
		//  in Run->Run Configurations->Arguments->VM Arguments, enter -ea
		//  assert(false);
		
		/*** CALL YOUR OWN TESTING METHODS HERE ***/
		
		/* TEST PLANS
		  ASPECT 1: Check TooManyException
		            How: Create a Javopoly game
		                 In board initialisation, overload with gaols more than 1 
		                 Check if TooManyException is caught properly
		  ASPECT 2: Check if a property is buyable
		            How: Create a Javopoly game with 2 players
		                 Let a player buy a property and store the square index
		                 Move another player to the same square
		                 Assert to see if assert error is thrown
		  ASPECT 3: Simulate ReceiveAllPlayers
		            How: Create a Javopoly with 4 players
		                 Find the sqaure index which has ReceiveAllPlayers card
		                 Move a player to that square
		                 Check functionality of ReceiveAllPlayers
		  ASPECT 4: Output Players
		            How: Create a Javopoly with 4 players
		                 Play the game for some time
		                 In a player's turn, do dump option
		                 Ascertain if players information is written to a file
		 */
	}

}
