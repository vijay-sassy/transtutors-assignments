package game;


import java.util.Random;

import squares.Property;

//static boolean COLOUR_IS_USED[COLOUR.values().length()];

public class Player {
	
	/**
	 * possible player token colours
	 */
	public enum COLOUR {RED,YELLOW,GREEN,BLUE,BLACK,WHITE,PINK,PURPLE};
	/**
	 * maximum number of players
	 */
	static final public int MAX_NUM_PLAYERS = COLOUR.values().length;
	/**
	 * array to keep track of which tokens have been assigned already
	 */
	private static boolean[] COLOUR_IS_USED = new boolean[MAX_NUM_PLAYERS];
	/**
	 * find a token which hasn't been taken yet
	 * @return
	 */
	private static Random RNG = new Random();
	private COLOUR getUnusedColour(){
		int remaining = MAX_NUM_PLAYERS-numPlayers;
		if(remaining<1) return null;
		//int which = Prob.getRandom(1, remaining); //DiceRoller.roll(1,remaining);
		int which = RNG.nextInt(remaining)+1;
		for(int i=0; i<MAX_NUM_PLAYERS; i++){
			if (!COLOUR_IS_USED[i]){
				if (which--==1) {
					COLOUR_IS_USED[i] = true;
					return COLOUR.values()[i];
				} 				
			}
		}
		System.out.println("should never see this");
		return null;
	}
	/**
	 * total number of Players that have been constructed
	 */
	static private int numPlayers = 0;
	
	/**
	 * maximum number of properties a Player can own
	 */
	static public final int MAX_NUM_PROPERTIES = 8;
	
	/**
	 * how much money each Player starts with
	 */
	static public final double INITIAL_CASH_BALANCE = 1500.0;
	
	/**
	 * the unique numeric ID of the player
	 */
	private int ID;
	/**
	 * what colour token this player has
	 */
	private COLOUR tokenColour;
	/**
	 * the PLayer's position on the board (index of square occupied)
	 */
	private int squareIndex;
	/**
	 * the Player's current stash of money
	 */
	private double cashBalance;
	/**
	 * Properties owned by the Player
	 */
	private Property[] ownedProperty;
	/**
	 * how many Properties owned by the Player
	 */
	private int numProperties;
	/**
	 * Which game board this Player is associated with
	 */
	private JavopolyGame theGame=null;
	
	/**
	 * Return the Player's ID as a printable String
	 * @return the String
	 */
	public String playerID(){
		return "player " + ID;
	}
	/**
	 * Return name of square on which player currently sits
	 */
	public String playerPosition(){
		String str = "";
		if (theGame==null){
			// don't have access to board, print position index
			str += "  position index " + squareIndex;
		}else{
			// we have access to board;  get the position's name from this.
			str += "  Currently on ";
			String foo = theGame.getBoard().getSquare(squareIndex).toString();
			str += foo;
		}
		return str;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = playerID() + " (" + tokenColour + " token)";

		str += playerPosition();
		str += "  liquid cash $" + String.format("%.2f", cashBalance);
		
		return str;
	}
	/**
	 * Return a list of Properties owned by this Player
	 * @return  the list, as a String
	 */
	public String listOfProperties(){
		String str = "";
		for(int i=0; i<numProperties; i++){
			str += "\t" + ownedProperty[i] + "\n";
		}
		return str;
	}
	
	// 15/2/18
	/**
	 * Create a Player and assign it to a Javopoly game
	 * @param game  the JavopolyGame this Player is playing on
	 * @throws OutOfTokensException  if there are no more free Players
	 */
	public Player(JavopolyGame game) throws OutOfTokensException {
		this();
		theGame = game;
	}
	/**
	 * Create a Player,  but do not assign it to a particular game
	 * @throws OutOfTokensException  if there are no more free Players
	 */
	public Player() throws OutOfTokensException {
		if (numPlayers==MAX_NUM_PLAYERS)
			throw new OutOfTokensException("no more unused player tokens");
		tokenColour = getUnusedColour();  // want numPlayers to be unaltered here
		ID = ++numPlayers;  // now we can increment it
		squareIndex = 0;  // always create at first square
		
		// perhaps we want the BoardSquare itself, rather than / as well as  the index?
		
		ownedProperty = new Property[MAX_NUM_PROPERTIES];
		numProperties = 0;
		
		cashBalance = INITIAL_CASH_BALANCE;
	}

	/**
	 * I don't think we need this.  I am having a C++ hangover.
	 * @param other
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private Player(Player other) throws Exception {
		throw new Exception("copy construction is prohibited.");
	}	
	
	/**
	 * return the unique ID of the Player
	 * @return the ID
	 */
	public int getID(){ return ID; }
	/** 
	 * return the colour of this Player's token
	 * @return  the colour
	 */
	public COLOUR getColour(){ return tokenColour; }
	
	/**
	 * how much money this Player has
	 * @return  the money amount
	 */
	public double getCash(){ return cashBalance;}
	/** 
	 * increase the Player's stash by a given amount
	 * @param amt  the amount of money 
	 */
	public void addToCash(double amt){ cashBalance +=  amt;}
	/** decrease the Player's stash by a given amount
	 * @param amt  the amount of money
	 */
	public void decreaseCash(double amt){ cashBalance -= amt; }
	
	/**
	 * Players current position on the board, or -1 if not associated with a JavopolyGame
	 * @return
	 */
	public int getSquareIndex(){
		if (theGame==null) return -1;
		return squareIndex;
	}
	/**
	 * set the Player's position.  
	 * THIS NOT BEING USED
	 * @param ix  index of square  being set as position.
	 * @return  true if the index is valid, false if not (and set operation fails).
	 *
	private boolean setSquareIndex(int ix){
		if ((ix<0)||(ix>=JavopolyGame.BOARDSIZE)) return false;
		squareIndex = ix;
		return true;
	}*/
	/**
	 * Increase the Player's position by a number of steps;  wrapping around to the start
	 * of the board if the end-of-board limit is passed.
	 * @param steps  number of spaces to go forwards
	 * @return  the new position, of -1 if the Player is not on a board.
	 */
	public int advancePosition(int steps){
		if (theGame==null) return -1;
		squareIndex = (squareIndex+steps)%GameBoard.BOARDSIZE;
		return squareIndex;
	}
	
	/**
	 * add a Property to the Player's list of Properties
	 * @param pty  the Property
	 * @return  true if success; false on fail (maximum number of Properties exceeded)
	 */
	public boolean addToPortfolio(Property pty){
		if (numProperties==MAX_NUM_PROPERTIES) return false;
		ownedProperty[numProperties++] = pty;
		pty.setOwner(this);
		return true;
	}
	// Note: this would be much simpler with an ArrayList.....
	/**
	 * remove a Property from Player's list of Properties.
	 * THIS NOT CURRENTLY IN USE?
	 * @param pty  whicih Property to delete from list
	 * @return  true if successful, false if not (eg Property wasn't on list)
	 
	public boolean removeFromPortfolio(Property pty){
		for(int i=0; i<numProperties; i++){
			if (ownedProperty[i].equals(pty)){  //  == ??
				ownedProperty[i] = null;
				for(int j=i+1; j<numProperties; j++)
					ownedProperty[j-1] = ownedProperty[j];
				numProperties--;
				return true;
			}
		}
		return false;
	}*/
	
	

	/**
	 * return the number of properties owned by this player
	 * @return number of properties
	 */
	public int getNumProperties(){
		return numProperties;
	}
	/**
	 * Determine the number of owned properties having same type as a supplied property
	 * @param reference  a Property
	 * @return  number of owned properties of same type
	 */
	public int getNumPropertiesOfSameType(Property reference){
		int numOfType = 0;
		for(Property owned: ownedProperty){
			if (owned==null) continue;  // break should work since array filled in order
			if (owned.getClass().equals(reference.getClass()) )
				numOfType++;
		}
		return numOfType;
	}

	
	///////////////////////////////////////////////////////////////////////////
	//
	// TEST DRIVER
	
	public static void main(String[] args){
		Player[] plays = new Player[9];
		
		int i=0;
		try{
			for(i=0; i<9; i++){
				System.out.println("i=" + i);
				plays[i] = new Player();
				//System.out.println("FII " + plays[i]);
			}
		} catch (OutOfTokensException e) { // need a subtype here 
			System.out.println("All players allocated. i is " + i);
		}
		for(int j=0; j<i; j++)
			System.out.println(plays[j]);
	}

}

////////////////////////////////////////////////////////////

class OutOfTokensException extends Exception{
	OutOfTokensException(String msg){
		super(msg);
	}
}
