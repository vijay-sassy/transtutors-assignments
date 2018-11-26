package cards;

import game.JavopolyGame;
import game.Player;

/**
 * It's a Lucky Card where the landed player
 * pays a fine amount to bank 
 */
public class PayToBank extends LuckyCard {
    private double fineAmt;
	public PayToBank(String msg, double amt) {
		super(msg);
		fineAmt = amt;
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		ply.decreaseCash(fineAmt);
		System.out.println(fineAmt + " is paid to bank");
		return true;
	}
	
    
	@Override
	public String toString() {
		return "PayToBank Amount: $" + fineAmt;
	}

}
