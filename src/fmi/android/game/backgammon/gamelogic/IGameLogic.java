package fmi.android.game.backgammon.gamelogic;

import fmi.android.game.backgammon.client.*;

public interface IGameLogic {
	/**
	 * Checks if the requested move is legal.
	 */
	public boolean isValid(Move move, Board board);

	/**
	 * Checks if the player can make a move.
	 */
	public boolean canMove(Board b);

	/**
	 * Rolls the dices.
	 */
	public void rollDices(Dice a, Dice b);

	/**
	 * Rolls the dices and decides which player starts first.
	 */
	public void decideFirstPlayer(Dice a, Dice b);

}
