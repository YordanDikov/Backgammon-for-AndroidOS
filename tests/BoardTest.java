import static org.junit.Assert.*;
import org.junit.Test;
import fmi.android.game.backgammon.client.*;

public class BoardTest {

	@Test
	public void testBoardConstructor() {
		Board testBoard = new Board();
		assertArrayEquals(Board.BACKGAMMON_STARTING_TABLE, testBoard.getBoardPlaces());
	}

	@Test
	public void testGetBoardPlacesReadOnly(){
		Board testBoard = new Board();
		byte[] boardPlaces = testBoard.getBoardPlaces();
		boardPlaces[4]++;
		assertArrayEquals(Board.BACKGAMMON_STARTING_TABLE, testBoard.getBoardPlaces());
	}
}
