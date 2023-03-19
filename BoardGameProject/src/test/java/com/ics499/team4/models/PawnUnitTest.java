package com.ics499.team4.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.team4.model.Board;
import com.team4.model.Pawn;
import com.team4.model.Piece;
import com.team4.model.Space;

public class PawnUnitTest {
	
	Board board;
	Piece pawn;
	
	@BeforeEach
	public void setup() {
		board = new Board();
		pawn = board.getSpace(1, 2).getPiece();
	}
	
	@Test
	public void testSettersAndGetters() {
		pawn.setColor(Piece.Color.BLACK);
		pawn.setType(Piece.Type.PAWN);
		pawn.setCurrentSpace(board.getSpace(1, 3));
		pawn.setBoard(board);
		((Pawn) pawn).setHasMoved(true);
		
		assertEquals(pawn.getColor(), Piece.Color.BLACK);
		assertEquals(pawn.getType(), Piece.Type.PAWN);
		assertEquals(pawn.getCurrentSpace().getX(), 1);
		assertEquals(pawn.getCurrentSpace().getY(), 3);
		assertEquals(pawn.getBoard(), board);
		assertTrue(((Pawn) pawn).getHasMoved());
	}
	
	@Test
	public void testGetPossibleMoves() {
		List<Space> correctSpaces = new ArrayList<>();
		correctSpaces.add(board.getSpace(1, 4));
		correctSpaces.add(board.getSpace(1, 3));
		
		List<Space> spacesFromCall = pawn.findPossibleMoves();
		
		assertEquals(correctSpaces, spacesFromCall);
	}
	
}
