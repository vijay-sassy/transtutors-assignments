package squares;


//import game.Player.COLOUR;
import game.JavopolyGame;
import game.Player;

public class BoardSquare {

	/**
	 * The name of the square, e.g. "Main Street"
	 */
	private String name;
	
	/**
	 * the JavopolyGame to which this BoardSquare belongs
	 */
	protected JavopolyGame theGame;
	
	/**
	 * Whether or not this Property can be bought
	 * @return true if can be bought;  false if not.
	 */
	public boolean isBuyable(Property property, Player plin){
		if(!property.isOwned() && plin.getCash()>= property.cost && 
		  plin.getNumProperties() < plin.MAX_NUM_PROPERTIES) {
		  return true;
		}
		return false; 
	} 
      
	/**
	 * Take care of any actions when a Player leaves this square
	 * @param plin  the Player
	 * @return true if Player successfully leaves the square, false if not
	 */
	public boolean handlePlayerLeaving(Player plin){
		if(plin.getCash() < 0) {
			return false;
		}
		return true;
	}
	/**
	 * Take care of any actions when a Player leaves this square
	 * THIS IS NOT IN USE
	 * @param colour
	 * @return  true if Player successfully leaves the square, false if not
	 */
/*	public boolean handlePlayerLeaving(COLOUR colour){
		return true;
	}
*/	/////////////////
	
	/**
	 * Perform any actions required when a Player enters this square
	 * @param plin  the Player  (not the ID, the actual Player)
	 */
	public void handlePlayerEntry(Player plin){ //, GameBoard gb){
		//System.out.println("default entry handler: do nothing");
		return;
	}
	
	/////////////////
	
	/**
	 * Create a new BoardSquare
	 * @param nom  the name of this Square, e.g. "Free Parking"
	 * @param gb  the Javopoly Game to which this BoardSquare will belong
	 */
	public BoardSquare(String nom, JavopolyGame gb) {
		name = nom;
		//numOccupants = 0;
		//occupants = new Player[Player.MAX_NUM_PLAYERS];
		theGame = gb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = name;
		return str;
	}
	/**
	 * return name of BoardSquare as a String
	 * Sometimes we only want the name, not all the details.
	 */
	public String getName(){
		return name;
	}
}
