package squares;

import game.JavopolyGame;
import game.Player;

/**
 * This is a type of property
 */
public class Street extends Property {
	public double cost;

	public Street(String nom, double price, JavopolyGame gb) {
		super(nom, price, gb);
		cost = price;
	}
	
	@Override
	public double computeRent(Player owner) {
        return cost / 10;
	}

	public String toString(){ 
		return "Street";
	}
}
