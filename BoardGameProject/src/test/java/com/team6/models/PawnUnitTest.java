package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import com.team6.model.pieces.Pawn;
import com.team6.model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnUnitTest {
	
	Board board;
	Piece pawn;
	
	@BeforeEach
	public void setup() {
		board = new Board();
		pawn = board.findSpace(1, 2).getPiece();
	}
	
	@Test
	public void testSettersAndGetters() {
		pawn.setColor(Color.BLACK);
		pawn.setType(Type.PAWN);
		pawn.setCurrentSpace(board.findSpace(1, 3));
		pawn.setBoard(board);
		((Pawn) pawn).setHasMoved(true);
		
		assertEquals(pawn.getColor(), Color.BLACK);
		assertEquals(pawn.getType(), Type.PAWN);
		assertEquals(pawn.getCurrentSpace().getX(), 1);
		assertEquals(pawn.getCurrentSpace().getY(), 3);
		assertEquals(pawn.getBoard(), board);
		assertTrue(((Pawn) pawn).getHasMoved());
	}
	
	@Test
	public void testGetPossibleMoves() {
		List<Space> correctSpaces = new ArrayList<>();
		correctSpaces.add(board.findSpace(1, 4));
		correctSpaces.add(board.findSpace(1, 3));
		
		List<Space> spacesFromCall = pawn.findPossibleMoves();
		
		assertEquals(correctSpaces, spacesFromCall);
	}
	
}
