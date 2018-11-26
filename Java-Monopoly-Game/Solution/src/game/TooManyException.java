package game;

/**
 * This is the custom exception class
 * which throws an exception when a 
 * particular threshold is met
 */
public class TooManyException extends Exception {

	public TooManyException(String msg) {
		super(msg);
	}
}
