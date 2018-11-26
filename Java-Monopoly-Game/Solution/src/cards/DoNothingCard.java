package cards;
import game.JavopolyGame;
import game.Player;

public class DoNothingCard extends LuckyCard {

	public DoNothingCard(String msg){
		super(msg);
	}

	@Override
	public boolean enactCard(Player ply, JavopolyGame gb) {
		//System.out.println(this.message + "  Nothing to do.");
		System.out.println("\tNothing to do.");
		return true;
	}
}
