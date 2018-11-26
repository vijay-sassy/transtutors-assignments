package squares;

import game.JavopolyGame;
import game.Player;

/**
 * This is a type of property
 */
public class Business extends Property {
	private String name;
	public double cost;
	private JavopolyGame game;
	public Business(String nom, double price, JavopolyGame gb) {
		super(nom, price, gb);
		game = gb;
	}	
	/**
	 * The method computes the rent
	 */
	@Override
	public double computeRent(Player owner) {
		Business bs = new Business(name,cost,game);
		int propertySize = owner.getNumPropertiesOfSameType(bs);
		return 10 * game.getLastDiceRoll() * propertySize;
	}

	public String toString(){ 
		return "Business: " + name;
	}
}
