package cards;
import game.JavopolyGame;
import game.Player;

public abstract class LuckyCard {

	String message;
	public LuckyCard(String msg) {
		message = msg;
	}

	public String toString(){  // this will be overridden in most subclasses.
		return message;
	}
	public void showMessage(){
		System.out.println(this);  // uses overridden toString
	}
	
	/**
	 * This routine performs the actions of the LuckyCard to the supplied Player.
	 * The JavopolyGame must also be passed in so the LuckyCard can access its members
	 * if needed.  It can be assumed that the supplied Player belongs to the supplied
	 * JavopolyGame.
	 * @param ply 	Player
	 * @param gb 	corresponding JavopolyGame
	 * @return 		true on success.
	 */
	public abstract boolean enactCard(Player ply, JavopolyGame gb);
	
}
