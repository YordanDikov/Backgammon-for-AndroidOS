package fmi.android.game.backgammon.client;

import fmi.android.game.backgammon.*;

/**
 * Board places are numbered from 1 to 24. By number 1 is marked the place,
 * where the two lonely black pieces stand. Numbering the board places goes in
 * the direction of movement of the black player. White pieces are marked with
 * positive numbers. Black pieces are marked with negative numbers. Absolute
 * value means number of pieces on that spot.
 */
public class Board {

	// ----------------------------------------
	// CLASS CONSTANTS
	// ----------------------------------------

	public static final byte BLACK_PIECE = -1;
	public static final byte WHITE_PIECE = 1;
	public static final byte EMPTY_SPACE = 0;
	private static final byte[] BACKGAMMON_STARTING_TABLE = { 0, -2, 0, 0, 0,
			0, 5, 0, 3, 0, 0, 0, -5, 5, 0, 0, 0, -3, 0, -5, 0, 0, 0, 0, 2 };

	private byte[] boardPlaces;
	private byte hitWhite;
	private byte hitBlack;
	private byte takenWhite; // are those two
	private byte takenBlack; // needed?

	public Board() {
		// sets the board in the initial game state
		hitWhite = 0;
		hitBlack = 0;
		takenWhite = 0;
		takenBlack = 0;
		boardPlaces = new byte[25];// BACKGAMMON_STARTING_TABLE;
		for (int i = 0; i < boardPlaces.length; i++)
			boardPlaces[i] = BACKGAMMON_STARTING_TABLE[i];
	}

	public byte getHitWhite() {
		return hitWhite;
	}

	public byte getHitBlack() {
		return hitBlack;
	}

	public byte getTakenWhite() {
		return takenWhite;
	}

	public byte getTakenBlack() {
		return takenBlack;
	}

	public byte[] getBoardPlaces() {
		return boardPlaces.clone();
	}

	public boolean pieceGoesOut(Move move){
		
		byte startPosition = move.getStart();
		byte endPosition = move.getEnd();
		
		//black piece going out of the field
		if(boardPlaces[startPosition]<0)
			if(endPosition>24) return true;
		
		//white piece going out of the field
		if(boardPlaces[startPosition]>0)
			if(endPosition<1) return true;
		
		return false;
	}
	
	public void makeMove(Move move) throws MoveNotLegalException {
		byte start = move.getStart();
		byte end = move.getEnd();

		if (start > 24)
			throw new MoveNotLegalException("Not existing starting place.");

		if (boardPlaces[start] == 0)
			throw new MoveNotLegalException("Board place is empty.");

		if (boardPlaces[start] < 0) { // move from pile of black pieces

			// we believe, that GameLogic allows this move
			if (end > 24) {
				boardPlaces[start] -= BLACK_PIECE;
				takenBlack++;
				return;
			}

			if (boardPlaces[end] > WHITE_PIECE)
				throw new MoveNotLegalException();

			
			if (boardPlaces[end] == WHITE_PIECE) {
				hitWhite++;
				boardPlaces[start] -= BLACK_PIECE;
				boardPlaces[end] = BLACK_PIECE;
				return;
			}
			boardPlaces[start] -= BLACK_PIECE;
			boardPlaces[end] += BLACK_PIECE;
			return;
		}

		// else the move is from a pile of white pieces

		if (end < 1) {// we believe, that GameLogic allows this move
			boardPlaces[start] -= WHITE_PIECE;
			takenWhite++;
			return;
		}
		if (boardPlaces[end] < BLACK_PIECE)
			throw new MoveNotLegalException();
		
		if (boardPlaces[end] == BLACK_PIECE) {
			hitBlack++;
			boardPlaces[start] -= WHITE_PIECE;
			boardPlaces[end] = WHITE_PIECE;
			return;
		}
		boardPlaces[start] -= WHITE_PIECE;
		boardPlaces[end] += WHITE_PIECE;
		return;

	}

	public byte getPiecesColor(int position)
	{
		if(boardPlaces[position]>0) return WHITE_PIECE;
		if(boardPlaces[position]<0) return BLACK_PIECE;
		return EMPTY_SPACE;
	}
	
	public byte getPiecesCount(int position)
	{
		return (byte) Math.abs(boardPlaces[position]);
	}
}
