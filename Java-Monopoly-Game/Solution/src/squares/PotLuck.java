package squares;

import game.JavopolyGame;

/**
 * This is a type of board square
 */
public class PotLuck extends BoardSquare {
    private String name;
	public PotLuck(String nom, JavopolyGame gb) {
		super(nom, gb);
		name = nom;
	}

	@Override
	public String toString() {
		return name;
	}
}
