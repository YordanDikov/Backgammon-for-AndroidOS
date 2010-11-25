package fmi.android.game.backgammon;

public class MoveNotLegalException extends Exception {
	public MoveNotLegalException(){
		super("Move Not Legal");
	}
	
	public MoveNotLegalException(String msg){
		super("Move Not Legal -> " + msg);
	}
}
