package cards;

import game.JavopolyGame;
import game.Player;

/**
 * It's a lucky card where a landed player
 * rolls the dice again
 */
public class RollAgain extends LuckyCard {

	public RollAgain(String msg) {
		super(msg);
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		int roll = gb.rollTheDice();
		System.out.println("dice roll is " + roll);
		System.out.println("Lucky Card - Rolled Again");
		return false;
	}

	@Override
	public String toString() {
		return message;
	}
}
