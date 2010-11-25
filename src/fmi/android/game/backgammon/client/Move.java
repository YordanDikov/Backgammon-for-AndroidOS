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
	// todo think of convenient way to represent the position of the hit pieces
	private byte position;
	/**
	 * Represents the number of places the piece is about to be moved.
	 */
	private byte distance;

	public Move(byte position, byte distance) {
		this.position = position;
		this.distance = distance;
	}

	public byte getPosition() {
		return this.position;
	}

	public byte getDistance() {
		return this.distance;
	}

}
