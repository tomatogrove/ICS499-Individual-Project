package com.team4.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import java.util.ArrayList;
//import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KingTest {
	
	Board board;
 	Piece king;

	@BeforeEach
	public void setup() {
		board = new Board();
		king = board.getSpace(1, 1).getPiece();
		
	}
	
	
	@Test
 	public void testKingSettersAndGetters() {
 		king.setColor(Piece.Color.WHITE);
 		king.setType(Piece.Type.KING);
 		king.setCurrentSpace(board.getSpace(1, 1));
 		king.setBoard(board);
 		((King) king).setHasMoved(true);
 		
 		assertEquals(Piece.Color.WHITE, king.getColor());
 		assertEquals(Piece.Type.KING, king.getType());
 		assertEquals(king.getCurrentSpace().getX(), 1);
 		assertEquals(king.getCurrentSpace().getY(), 1);
 		assertEquals(board, king.getBoard());
 		assertTrue(((King) king).getHasMoved());
 		
 	}

}
