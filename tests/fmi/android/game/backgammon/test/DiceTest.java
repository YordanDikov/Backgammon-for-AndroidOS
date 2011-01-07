package fmi.android.game.backgammon.test;

import static org.junit.Assert.*;
import org.junit.Test;
import fmi.android.game.backgammon.client.Dice;

public class DiceTest {

	@Test
	public void testRoll() {
		Dice testDice = new Dice();
		byte result = testDice.roll();
		assertTrue(result>= 1 && result <= 6);
	}

}
