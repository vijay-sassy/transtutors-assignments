package squares;

import game.JavopolyGame;

/**
 * This is a type of board square
 */
public class Gaol extends BoardSquare {
	private String name;
	private boolean isInGaol = false;

	public Gaol(JavopolyGame gb) {
		super("Gaol", gb);
        name = "Gaol";
	}

	@Override
	public String toString() {
		return name;
	}
}
