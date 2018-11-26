package cards;

import game.JavopolyGame;
import game.Player;

/**
 * It's a lucky card where a landed player
 * receives amount from bank
 */
public class ReceiveFromBank extends LuckyCard {
    private double awardAmt;
	public ReceiveFromBank(String msg, double amt) {
		super(msg);
		awardAmt = amt;
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		ply.addToCash(awardAmt);
		System.out.println(awardAmt + " is received from bank");
		return true;
	}

	@Override
	public String toString() {
		return "ReceiveFromBank Amount: $" + awardAmt;
	}
}
