package fmi.android.game.backgammon.gamelogic;

import fmi.android.game.backgammon.client.Board;
import fmi.android.game.backgammon.client.Dice;
import fmi.android.game.backgammon.client.Move;

public class BackgammonGameLogic implements IGameLogic {

	private Board board;
	private Dice firstDice, secondDice;

	public BackgammonGameLogic(Board b) {
		board = b;
		firstDice = new Dice();
		secondDice = new Dice();
	}

	@Override
	public boolean isValid(Move move) {

		if (board.pieceGoesOut(move))
			return true;

		byte startPiece = board.getPiecesColor(move.getStart());
		byte endPiece = board.getPiecesColor(move.getEnd());

		if (move.getStart() > 24)
			return false;

		if (startPiece == Board.EMPTY_SPACE)
			return false;

		if (startPiece == endPiece)
			return true;
		else {
			if (board.getPiecesCount(move.getEnd()) > 1)
				return false;
			else
				return true;
		}

	}

	@Override
	public boolean canMove(byte playerColor) {
		byte pieceColor;
		byte firstDiceValue = firstDice.getValue();
		byte secondDiceValue = secondDice.getValue();

		if (playerColor == Board.BLACK_PIECE)
			for (byte i = 1; i < 25; i++) {
				pieceColor = board.getPiecesColor(i);
				if (pieceColor == playerColor) {
					if (isValid(new Move(i, (byte) (i + firstDiceValue))))
						return true;
					if (isValid(new Move(i, (byte) (i + secondDiceValue))))
						return true;
				}
			}

		else
			for (byte i = 24; i >0; i--) {
				pieceColor = board.getPiecesColor(i);
				if (pieceColor == playerColor) {
					if (isValid(new Move(i, (byte) (i - firstDiceValue))))
						return true;
					if (isValid(new Move(i, (byte) (i - secondDiceValue))))
						return true;
				}
			}
		
		return false;
	}

	@Override
	public void rollDices() {
		firstDice.roll();
		secondDice.roll();
	}

	@Override
	/**
	 * Returns true if first player should be white and false otherwise.*/
	public boolean decideFirstPlayer() {
		rollDices();
		if(firstDice.getValue()>secondDice.getValue())return true;
		return false;

	}

}
