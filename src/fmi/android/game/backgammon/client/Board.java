package fmi.android.game.backgammon.client;

import fmi.android.game.backgammon.exception.*;

public class Board {
	/*
	  * Board places are numbered from 1 to 24.
	  * By number 1 is marked the place, where the two lonely black pieces stand.
	  * Numbering the board places goes in the direction of movement of the black player.
	  * White pieces are marked with positive numbers.
	  * Black pieces are marked with negative numbers.
	  * Absolute value means number of pieces on that spot.
	*/

	private byte[] boardPlaces;
	private byte hitWhite;
	private byte hitBlack;
	private byte takenWhite; // are those two
	private byte takenBlack; // needed?
	private static final byte SINGLE_BLACK_PIECE = -1;
	private static final byte SINGLE_WHITE_PIECE = 1;
	public static final byte[] BACKGAMMON_STARTING_TABLE =  {0,-2,0,0,0,0,5,0,3,0,0,0,-5,5,0,0,0,-3,0,-5,0,0,0,0,2};

	public Board(){ //sets the board in the initial game state
		hitWhite = 0;
		hitBlack = 0;
		takenWhite = 0;
		takenBlack = 0;
		boardPlaces = new byte[25];//BACKGAMMON_STARTING_TABLE;
		for(int i=0;i<boardPlaces.length;i++)
			boardPlaces[i]=BACKGAMMON_STARTING_TABLE[i];
	}

	public byte getHitWhite(){
		return hitWhite;
	}
	
	public byte getHitBlack(){
		return hitBlack;
	}

	public byte getTakenWhite(){
		return takenWhite;
	}
	
	public byte getTakenBlack(){
		return takenBlack;
	}

	public byte[] getBoardPlaces(){
		return boardPlaces.clone();
	}

	public void makeMove(byte from, byte with) throws MoveNotLegalException{
		
		if(from > 24) throw new MoveNotLegalException("Not existing starting place.");
		
		if(boardPlaces[from] == 0) throw  new MoveNotLegalException("Board place is empty.");
		
		if(boardPlaces[from] < 0){ // move from pile of black pieces
			if(from + with > 24) {//we believe, that GameLogic allows this move
				boardPlaces[from] -= SINGLE_BLACK_PIECE;
				takenBlack++;
				return;
			}
			if(boardPlaces[from + with] > 1) throw new MoveNotLegalException();
			if(boardPlaces[from + with] == SINGLE_WHITE_PIECE){
				hitWhite++;
				boardPlaces[from] -= SINGLE_BLACK_PIECE;
				boardPlaces[from + with] = SINGLE_BLACK_PIECE;
				return;
			}
			boardPlaces[from] -= SINGLE_BLACK_PIECE;
			boardPlaces[from + with] += SINGLE_BLACK_PIECE;
			return;
		}
		
		//else the move is from a pile of white pieces

		if(from - with < 1) {//we believe, that GameLogic allows this move
			boardPlaces[from] -= SINGLE_WHITE_PIECE;
			takenWhite++;
			return;
		}
		if(boardPlaces[from - with] < SINGLE_BLACK_PIECE) throw new MoveNotLegalException();
		if(boardPlaces[from - with] == SINGLE_BLACK_PIECE){
			hitBlack++;
			boardPlaces[from] -= SINGLE_WHITE_PIECE;
			boardPlaces[from + with] = SINGLE_WHITE_PIECE;
			return;
		}
		boardPlaces[from] -= SINGLE_WHITE_PIECE;
		boardPlaces[from + with] += SINGLE_WHITE_PIECE;
		return;		

		
	}
}
