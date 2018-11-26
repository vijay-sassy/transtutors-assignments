package utility;

//import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * @author searlesj
 *
 */
public class DiceRoller {

	// the Random Number Generator, for normal operation
	private Random RNG = new Random();
	//
	// data for hardwired-sequence mode of operation
	private int[] intSequence = null;
	private int seqIx = 0;
	//
	// data for user-input mod of operation
	private boolean ask = false;
	private Scanner scin = null;

	// how many dice to roll, and how many sides each one has.
	private int nDice, nSides;
	
	public String toString(){
		return String.format("(%dd%d)",nDice,nSides);
	}
	//////////////////////////////////////////////////////////
	
	private void setDice(int nd, int ns){
		nDice = nd;
		nSides = ns;
	}
	private void setDice(String diceStr){
		String[] nums = diceStr.split("d");
		nDice = Integer.parseInt(nums[0]);
		nSides = Integer.parseInt(nums[1]);
	}
	
	public DiceRoller(){
		setDice(2,6);
		setSeed();
	}
	
	public DiceRoller(String diceStr){
		setDice(diceStr);
		setSeed();
	}
	
	public DiceRoller(int nd,int ns){
		setDice(nd,ns);
		setSeed();
	}
	
	///////////////////////////////////////////////////////////////
	
	/**
	 * set the seed to the RNG so the same sequence is generated each time
	 * @param arg
	 */
	public void setSeed(long arg){
		this.setSeed();
		RNG.setSeed(arg);
	}
	/**
	 * setup RNG with arbitrary seed
	 */
	public void setSeed(){
		if (RNG==null) RNG = new Random();
		intSequence = null;
		if (scin!=null) {scin.close(); scin=null;}
	}
	/** 
	 * seed the RNG with a sequence
	 */
	public void setSeed(int[] seq){
		intSequence = seq.clone();
		seqIx = 0;  // 12/6/17
		RNG = null;
		if (scin!=null) {scin.close(); scin=null;}
	}
	/**
	 * set RNG to be user supplied   13/6/17
	 */
	public void setSeed(boolean promptUser){
		if (promptUser){
			intSequence = null;
			ask = true;
			scin = new Scanner(System.in);
		} else {
			setSeed(); // this also handles scin & sequence
			ask = false;
		}
	}

	
	////////////////////////////////////////////////////////////
	
	/**
	 * Return index of seed sequence, of -1 if sequence not presently used.
	 * @return index
	 */
	public int getIndex(){
		if (intSequence==null)
			return -1;
		else
			return seqIx;
	}


	private void printCaller(){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement s=null;
		// find first stack element not belonging to Thread or DiceRoller
		for(StackTraceElement se: elements){
			String classname = se.getClassName();
			if ( !((classname.contains("Thread")) || classname.contains("DiceRoller")) ){  // *** note hardwired class names
				s = se;
				break;
			}
		}
		if (s!=null)
			System.out.print("\tat " + s.getClassName() + "." + s.getMethodName()
			+ "(" + s.getFileName() + ":" + s.getLineNumber() + "): ");
		else System.out.print("\tDiceRoller: ");
	}


	//////////////////////////////////////////////////////////////////
	
	public int getDiceRoll(String diceStr){
		setDice(diceStr);
		return getDiceRoll();
	}
	public int getDiceRoll(int ndice, int nsides){
		setDice(ndice,nsides);
		return getDiceRoll();
	}
	
	public int getDiceRoll(){
		int randomNum=0;
		if (ask){
			// prompt user
			printCaller();
			System.out.println("");
			System.out.print("\tEnter int satisfying " + nDice + "d" + nSides + ": ");
			randomNum = scin.nextInt();
		} else if (intSequence!=null){
			//for(int d=0; d<nDice; d++){
				randomNum = intSequence[seqIx];
				seqIx = (seqIx+1)%intSequence.length;
			//}
		} else {
			for(int i=0; i<nDice; i++)
				randomNum += RNG.nextInt(nSides) + 1;
		}
		return randomNum;
	}
	
	//////////////////////////////////////////////////////////////////////

	// test routine
	/**
	 * @param args
	 */
	public static void main(String[] args){

		// test general rolling
		int ndice = 3;
		int nsides = 3;
		DiceRoller dr= new DiceRoller(ndice,nsides);
		System.out.println("dr: " + dr);
		int[] tally = new int[ndice*nsides+1];
		for (int i=0; i<1000; i++) 
			tally[dr.getDiceRoll()]++;
		for(int i=ndice; i<=ndice*nsides; i++)
			System.out.printf("%2d   %4d\n\n",i,tally[i]);
		
		// test roll with supplied parameters
		ndice = 2;
		nsides = 6;
		int[] tally2 = new int[ndice*nsides+1];
		for (int i=0; i<1000; i++) 
			tally2[dr.getDiceRoll(ndice,nsides)]++;
		for(int i=ndice; i<=ndice*nsides; i++)
			System.out.printf("%2d   %4d\n",i,tally2[i]);
		System.out.println(" dr now " + dr);
		
		// test sequence
		int[] seq = {3,8,9,3,6,6,6};
		dr.setSeed(seq);
		for(int i=0; i<seq.length*3; i++)
			System.out.print(dr.getIndex() + ":" + dr.getDiceRoll()+"   ");
		System.out.println("\n");
		
		// test usser input
		dr.setSeed(true);
		int rnum = dr.getDiceRoll();
		System.out.println("entered dice roll is " + rnum);
		dr.setSeed(false);
		rnum = dr.getDiceRoll();
		System.out.println("back to auto, dice roll is " + rnum);
	}



}
