package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class OutputPlayers {

	/**
	 * the filename to use when none is supplied
	 */
	final static String DEFAULT_FILENAME = "gameDump.txt";

	/**
	 * Open text file and write out current player information (using game's toString()).
	 * If this operation fails then "File dump failed" must be written to output
	 * along with any available diagnostic information.
	 * FILL IN THIS ROUTINE AND DELETE THE "not working" LINE
	 * @param filename  The file to open and write to.
	 */
	public static void writePlayersToFile(JavopolyGame game, String filename){
		writeOperation(game,filename);
	}
	
	/**
	 * Open text file and write out current player information (using game's toString()).
	 * If this operation fails then "File dump failed" must be written to output
	 * along with any available diagnostic information.
	 * The default file is written to.
	 * FILL IN THIS ROUTINE AND DELETE THE "not working" LINE
	 */
	public static void writePlayersToFile(JavopolyGame game){
		writeOperation(game, DEFAULT_FILENAME);
	}
	
	/**
	 * This does the actual write operation
	 * @param game The corresponding game
	 * @param filename Name of the file to be written
	 */
	public static void writeOperation(JavopolyGame game, String filename) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filename));
			writer.write(game.toString());
			System.out.println("Dump is written successfully");
		} catch (IOException e) {
			System.out.println("Dump isn't working - yet!");
			System.out.println("Exception: " + e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Exception: " + e.getMessage());
			}
		}
	}
}
