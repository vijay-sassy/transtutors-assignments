package squares;

import game.JavopolyGame;
import game.Player;

/**
 * This is a type of property
 */
public class BusStation extends Property {
	private String name;
	public double cost;
	private JavopolyGame game;

	public BusStation(String nom, double price, JavopolyGame gb) {
		super(nom, price, gb);
		name = nom;
		cost = price;
		game = gb;
	}

	@Override
	public double computeRent(Player owner) {
		BusStation bs = new BusStation(name,cost,game);
		int propertySize = owner.getNumPropertiesOfSameType(bs);
		return 50 * propertySize;	
	}

	public String toString(){ 
		return "Busstation: " + name;
	}
}
