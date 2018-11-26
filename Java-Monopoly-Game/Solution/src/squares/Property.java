package squares;



import game.JavopolyGame;
import game.Player;

/**
 * This is an abstract class. It
 * has properties used in the game
 */
public abstract class Property extends BoardSquare {
	private Player owner;
	public double cost;
	public Property(String nom, double price, JavopolyGame gb){
	  super(nom,gb);
	  cost = price;
	}
	
	public void setOwner(Player pl) {
	  owner = pl;	
	}

	public Player getOwner() {
		return owner;
	}

	public boolean isOwned() {
		return owner != null? true : false;
	}
	
	public abstract double computeRent(Player owner);
	
	public String toString(){ 
		return "Property";
	}
}
