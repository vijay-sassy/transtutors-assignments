package game;

import squares.*;

public class GameBoard {

	
	/**
	 * how many squares on the board.  This is fixed.
	 */
	public static final int BOARDSIZE = 32;
	/**
	 * All squares on the board
	 */
	private BoardSquare[] board;
	
	/**
	 * construct a GameBoard having BOARDSIZE squares.
	 */
	public GameBoard() {
		board = new BoardSquare[BOARDSIZE];
	}

	
	/** lookup a square of the board
	 * @param ix  which square
	 * @return  that square, or null if index was bad
	 */
	BoardSquare getSquare(int ix){  // note package access
		try{
			return board[ix];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	/**
	 * set a square of the board
	 * @param ix  which square on the board
	 * @param bsq  what to set in that square
	 * @return  true on success, false on failure
	 */
	boolean setSquare(int ix, BoardSquare bsq){ // note package access
		if ((ix<0)||(ix>=BOARDSIZE)) return false;
		board[ix] = bsq;
		return true;
	}
	
	//  *  *  *  *  *  *  *  *  *  *  *   *  *  *  *   *  * *  
	
	// defined constants for max # Businesses, BusStops and Gaols per game
	public static final int MAX_NUM_BUSINESS = 4;
	public static final int MAX_NUM_BUSSTATION = 4;
	public static final int MAX_NUM_GAOL = 1;
	
	public static int currentNumBusiness = 0;
	public static int currentNumBusStation = 0;
	public static int currentNumGaol = 0;
	
	static void throwException(String property) throws TooManyException {
	  throw new TooManyException("Maximum squares reached! Can't add " + property + "!"); 
	}
	
	static void setSquareForLimitedProperties(int position,BoardSquare bsq,GameBoard board)
	  throws TooManyException {
	  if(bsq instanceof Gaol) {
	    if(currentNumGaol < MAX_NUM_GAOL) {
	      board.setSquare(position,bsq);
	      ++currentNumGaol;
	    } else {
	      throwException(bsq.getClass().getName());
	    }
	  } else if(bsq instanceof Business) {
		  if(currentNumBusiness < MAX_NUM_BUSINESS) {
		      board.setSquare(position,bsq);
		      ++currentNumBusiness;
		    } else {
		      throwException(bsq.getClass().getName());
		    } 
	  } else {
		  if(currentNumGaol < MAX_NUM_BUSSTATION) {
		      board.setSquare(position,bsq);
		      ++currentNumBusStation;
		    } else {
		      throwException(bsq.getClass().getName());
		    }
	  }
	}
	
	// *** ADD NEW INSTANCE VARIABLES AND MEMBERS HERE TO ENFORCE THE ABOVE LIMITS
	// *** AND TO THROW A TooManyException IF THE USER TRIES TO ADD MORE THAN ALLOWED
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	// Some static routines to create and setup GameBoards
	
	/**
	 * Some test Javopply boards.  
	 * ONLY OPTION 0 IS ENABLED, UNCOMMENT THE OTHER OPTIONS TO USE THEM.
	 * YOU WILL NEED TO WRITE OTHER CLASSES TO USE THEM.
	 * @param flag  which board:  0,  or 1 or 2 (once you've written the code)
	 * @param jGame  the Javopoly game to which this board is attached
	 * @return the set-up GameBoard
	 */
	static GameBoard testInitialisation(int flag, JavopolyGame jGame){  // note package access
		GameBoard newBoard = new GameBoard();
		// set up the board
		for(int i=0; i<BOARDSIZE; i++){
			switch(flag){
			case 0:
				newBoard.setSquare(i, new BoardSquare("default square " + (i+1), jGame) );
				break;
		
			case 1:
				if (i%2==0) newBoard.setSquare(i,new BoardSquare("default square " + (i+1), jGame) );
				else newBoard.setSquare(i,new Street("boulevard " + (i+1), (double)200+10*i, jGame) );
				break;
			case 2:
				if (i%8==0) newBoard.setSquare(i, new BoardSquare("default square " + (i+1), jGame) );
				else if (i%8==3) {
					try {
//						newBoard.setSquare(i, new BusStation("bus stop " + (i+1), 200, jGame) );
						setSquareForLimitedProperties(i, new BusStation("bus stop " + (i+1), 200, jGame), newBoard);
					} catch (TooManyException e) {
						newBoard.setSquare(i, new BoardSquare("ruins of a bus stop " + (i+1), jGame) );
					}
				}
				else if (i%8==5) {
					try{
//						newBoard.setSquare(i, new Business("utility " + (i+1), 150, jGame) );
						setSquareForLimitedProperties(i, new Business("utility " + (i+1), 150, jGame), newBoard);
					} catch (TooManyException e) {
						newBoard.setSquare(i, new BoardSquare("boarded up premises " + (i+1),jGame) );
					}
				}
				else if (i%8==4){
					newBoard.setSquare(i, new PotLuck("Potluck " + (i+1), jGame) );
				}
				else newBoard.setSquare(i, new Street("boulevard " + (i+1), (double)200+10*i, jGame) );
				break;
			
			default:
				throw new IllegalArgumentException("no such test board option.  Have the cases been uncommented?");
			}

		}
		return newBoard;
	}


	static GameBoard mawsonLakesInitialisation(JavopolyGame jGame){   //note package access
		// set up the Javopoly board with sites from Mawson Lakes :)
		GameBoard newBoard = new GameBoard();
		try{
			int ix = 0;
			newBoard.setSquare(ix++, new BoardSquare("GO!", jGame));
			newBoard.setSquare(ix++, new Street("Goodall Parade",100,jGame));
			newBoard.setSquare(ix++, new Street("Hurtle Parade",120,jGame));
//			newBoard.setSquare(ix++, new BusStation("Stop 34L",200,jGame));
			setSquareForLimitedProperties(ix++, new BusStation("Stop 34L",200,jGame), newBoard);
			newBoard.setSquare(ix++, new PotLuck("Pot Luck A",jGame));
//			newBoard.setSquare(ix++, new Business("The Shopping Centre",150,jGame));
			setSquareForLimitedProperties(ix++,new Business("The Shopping Centre",150,jGame),newBoard);
			newBoard.setSquare(ix++, new Street("Light Common",140,jGame));
			newBoard.setSquare(ix++, new Street("Main Street",160,jGame));

			newBoard.setSquare(ix++, new BoardSquare("The Lake", jGame));
			newBoard.setSquare(ix++, new Street("The Strand",200,jGame));
			newBoard.setSquare(ix++, new Street("Peninsula Drive",220,jGame));
//			newBoard.setSquare(ix++, new BusStation("Campus Connector Stop",200,jGame));
			setSquareForLimitedProperties(ix++,new BusStation("Campus Connector Stop",200,jGame),newBoard);
			newBoard.setSquare(ix++, new PotLuck("Pot Luck B",jGame));
//			newBoard.setSquare(ix++, new Business("The Boat Cafe",150,jGame));
			setSquareForLimitedProperties(ix++,new Business("The Boat Cafe",150,jGame),newBoard);			
			newBoard.setSquare(ix++, new Street("Parkview Drive",240,jGame));
			newBoard.setSquare(ix++, new Street("Mawson Lakes Boulevard",260,jGame));

//			newBoard.setSquare(ix++, new Gaol(jGame));
			setSquareForLimitedProperties(ix++, new Gaol(jGame), newBoard);
			newBoard.setSquare(ix++, new Street("Garden Terrace",300,jGame));
			newBoard.setSquare(ix++, new Street("Capital Street",320,jGame));
//			newBoard.setSquare(ix++, new BusStation("Stop 34C",200,jGame));
			setSquareForLimitedProperties(ix++, new BusStation("Stop 34C",200,jGame), newBoard);
			newBoard.setSquare(ix++, new PotLuck("Pot Luck C",jGame));
//			newBoard.setSquare(ix++, new Business("The Planetarium",150,jGame));
			setSquareForLimitedProperties(ix++, new Business("The Planetarium",150,jGame), newBoard);
			newBoard.setSquare(ix++, new Street("Metro Parade",340,jGame));
			newBoard.setSquare(ix++, new Street("Elder Smith Road",360,jGame));

			newBoard.setSquare(ix++, new BoardSquare("The Plaza", jGame));
			newBoard.setSquare(ix++, new Street("Technology Drive",400,jGame));
			newBoard.setSquare(ix++, new Street("Park Way",440,jGame));
//			newBoard.setSquare(ix++, new BusStation("Mawson Interchange",200,jGame));
			setSquareForLimitedProperties(ix++, new BusStation("Mawson Interchange",200,jGame), newBoard);
			newBoard.setSquare(ix++, new PotLuck("Pot Luck D",jGame));
//			newBoard.setSquare(ix++, new Business("Technology Park Gymnasium",150,jGame));
			setSquareForLimitedProperties(ix++, new Business("Technology Park Gymnasium",150,jGame), newBoard);
			newBoard.setSquare(ix++, new Street("Third Avenue",480,jGame));
			newBoard.setSquare(ix++, new Street("Main North Road",500,jGame));
			//newBoard.setSquare(ix++,  new Business("Force an Exception",200,jGame));
			setSquareForLimitedProperties(ix++, new Business("Force an Exception",200,jGame), newBoard);
			
		} catch (TooManyException e) {
			System.out.println(e);
			System.out.println("Exception caught!");
			// kill program anyway;  just wanted to test exception
			System.exit(-1);
		} catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		return newBoard;
	}
}
