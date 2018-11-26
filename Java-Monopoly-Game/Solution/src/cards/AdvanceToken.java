package cards;

import game.JavopolyGame;
import game.Player;
/**
 * It is a LuckyCard intended to advance steps
 */
public class AdvanceToken extends LuckyCard {
	private int forwardCount;

	public AdvanceToken(String msg, int nAdvance) {
		super(msg);
		forwardCount = nAdvance;
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		boolean didAdvance = gb.movePlayer(ply, forwardCount);
		if(didAdvance) {
          System.out.println(ply.playerID() + " has advanced " + 
		    forwardCount + " steps");
		}
		return didAdvance;
	}

	@Override
	public String toString() {
		return "Forward Count: " + forwardCount;
	}
}
