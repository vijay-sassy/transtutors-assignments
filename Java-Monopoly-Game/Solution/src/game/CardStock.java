package game;


import java.util.Arrays;
import java.util.Collections;

import cards.*;


public class CardStock {

	/**
	 * array to hold the deck of LuckyCards
	 */
	private LuckyCard[] cards;
	private int upto = -1;
	
	/**
	 * get the number of LuckyCards in the deck
	 * @return  the number of cards
	 */
	public int getNumCards(){ return cards.length; }
	
	/** 
	 * constuct a new deck having ncard cards, and initialise with option 1
	 * @param ncard
	 */
	public CardStock(int ncard){
		this(ncard,1,true);
	}
	public CardStock(int ncard, boolean shuffleCards){
		this(ncard,1,shuffleCards);
	}
	/**
	 * construct a new deck having ncard cards, and specify how to initialise
	 * method 1:  all cards are DoNothing cards
	 * method 2:  use a set list of cards, padding deck with DoNothings if needed.
	 * METHOD 2 REQUIRES THE FOLLOWING CLASSES IN ORDER TO WORK:
	 *   ReceiveFromBank PayToBank AdvanceToken PayAllPlayers ReceiveAllPlayers RollAgain
	 * IT IS COMMENTED OUT; REMOVE THE BLOCK-COMMENT DELIMITERS WHEN YOU HAVE MADE ALL 
	 * OF THE REQUIRED CLASSES
	 * 
	 * @param ncard  number of cards in the deck
	 * @param initialise  which method, 1 or 2
	 * @param shuffleCards  true to shuffle cards after creation
	 */
	public CardStock(int ncard, int initialise, boolean shuffleCards) {
		cards = new LuckyCard[ncard];
		setDoShuffle(shuffleCards);
		switch(initialise){
		case 1:
			for(int i=0; i<cards.length; i++) 
				cards[i] = new DoNothingCard("do nothing #" + (i+1));
			break;
		
		case 2:
			try{
				cards[0] = new ReceiveFromBank("You receive dividend from stock",100.00);
				cards[1] = new PayToBank("You are fined for jaywalking", 50.00);
				cards[2] = new ReceiveFromBank("Your term deposit matures", 123.45);
				cards[3] = new PayToBank("You must pay council rates", 200.00);
				cards[4] = new AdvanceToken("Shove it brother, just keep walking", 5);
				cards[5] = new AdvanceToken("The road is slippery, slide on through", 1);
				cards[6] = new PayAllPlayers("It's your shout", 5.00);
				cards[7] = new ReceiveAllPlayers("Steal everyone's lunch money", 10.00);
				cards[8] = new RollAgain("Drink a double espresso");
				cards[9] = new RollAgain("Rise early in the morning");
				for (int i = 10; i < cards.length; i++)
					cards[i] = new DoNothingCard("do nothing #" + (i + 1));
			} catch(ArrayIndexOutOfBoundsException e){
				// do nothing;  we've filled the array.
			}
			break;
		
		default:
			throw new IllegalArgumentException("initialise must be 1 or 2. (Has option 2 been uncommented?)");
		}
		
		shuffleCards();
		
		upto = 0;
	}
	
	/**
	 * shuffle the cards array.
	 */
	private void shuffleCards(){
		if (doShuffle)
			Collections.shuffle(Arrays.asList(cards));
	}
	/**
	 * method to enable/suppress shuffling of cards - useful for debugging.
	 */
	public void setDoShuffle(boolean arg){
		doShuffle = arg;
	}
	private boolean doShuffle = true;
	
	/**
	 * Serve the next card from the stock.
	 * If last card is served, shuffle the deck and reset.
	 * @return  the LuckyCard
	 */
	public LuckyCard serveCard(){
		LuckyCard out = cards[upto++];
		if (upto==cards.length) {
			upto = 0;
			shuffleCards();
		}
		return out;
	}

	// ***** note that the LuckyCards must be invariant, since we are passing 'em out *****
}
