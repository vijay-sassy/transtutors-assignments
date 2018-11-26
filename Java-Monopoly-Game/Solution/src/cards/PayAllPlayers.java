package cards;

import game.JavopolyGame;
import game.Player;

/**
 * It's a Luck Card where the landed player
 * pays a fine to all other players
 */
public class PayAllPlayers extends LuckyCard {
    private double fineAmt;
	public PayAllPlayers(String msg, double amt) {
		super(msg);
        fineAmt = amt;
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		ply.decreaseCash(fineAmt);
		int nPlayers = gb.getNumPlayers();
		for(int i = 0; i < nPlayers; i++) {
			gb.getPlayer(i).addToCash(fineAmt);
		}
		System.out.println(fineAmt + " is paid to all");
		return true;
	}

	@Override
	public String toString() {
		return "PayAllPlayers Amount: $" + fineAmt;
	}

}
