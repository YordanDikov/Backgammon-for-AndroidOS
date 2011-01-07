/**
 * This class represents single dice. In usual game 2 instances of this class will be needed. 
 */
package fmi.android.game.backgammon.client;

import java.util.Random;

public class Dice {

	private Random generator;
	private byte value;
	
	public Dice() {
		generator = new Random();
	}

	/**
	 * This method generates random value for the dice in the interval 1-6 using
	 * java.util.Random class and returns the value as result.
	 */
	public byte roll() {
		value = (byte) (generator.nextInt(6) + 1);
		return value;
	}

	public byte getValue(){
		return value;
	}
}
