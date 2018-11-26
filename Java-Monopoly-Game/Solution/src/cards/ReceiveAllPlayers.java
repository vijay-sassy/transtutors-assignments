package cards;

import game.JavopolyGame;
import game.Player;

/**
 * It's a lucky card where a landed
 * player receives amount from all players
 */
public class ReceiveAllPlayers extends LuckyCard {
    private double awardAmt;
	public ReceiveAllPlayers(String msg, double amt) {
		super(msg);
		awardAmt = amt;
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		int nPlayers = gb.getNumPlayers();
		for(int i = 0; i < nPlayers; i++) {
			gb.getPlayer(i).decreaseCash(awardAmt);
		}
		ply.addToCash(awardAmt);
		System.out.println(awardAmt + " is received from all players");
		return true;
	}

	@Override
	public String toString() {
		return "ReceiveAllPlayers Amount: $" + awardAmt;
	}
}
