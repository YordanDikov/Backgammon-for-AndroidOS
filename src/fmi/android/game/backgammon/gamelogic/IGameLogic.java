package fmi.android.game.backgammon.gamelogic;

import fmi.android.game.backgammon.client.*;

public interface IGameLogic {
	/**
	 * Checks if the requested move is legal.
	 */
	public boolean isValid(Move move);

	/**
	 * Checks if the player can make a move.
	 */
	public boolean canMove(byte playerColor);

	/**
	 * Rolls the dices.
	 */
	public void rollDices();

	/**
	 * Rolls the dices and decides which player starts first.
	 */
	public boolean decideFirstPlayer();

}
