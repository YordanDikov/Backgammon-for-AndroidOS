package fmi.android.game.backgammon.client;

/**
 * This class is responsible for the presentation of the moves in game. The game
 * logic is responsible for checking the true validity of the values. This class
 * just represents convenient container for this values.
 */
public class Move {

	/**
	 * Represents the place on the board where is the piece that is about to be
	 * moved.
	 */
	// TODO think of convenient way to represent the position of the hit pieces
	private byte start;
	/**
	 * Represents the place on the board where the piece should land.
	 */
	private byte end;

	public Move(byte position, byte distance) {
		this.start = position;
		this.end = distance;
	}

	public byte getStart() {
		return this.start;
	}

	public byte getEnd() {
		return this.end;
	}

}
