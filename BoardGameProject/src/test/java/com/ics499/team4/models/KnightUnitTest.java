package com.ics499.team4.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.team4.model.Board;
import com.team4.model.Piece;
import com.team4.model.Space;

public class KnightUnitTest {

	Board board;
	Piece knight;
	
	@BeforeEach
	public void setup() {
		board = new Board();
		knight = board.getSpace(2, 1).getPiece();
	}
	
	@Test
	public void testSettersAndGetters() {
		knight.setColor(Piece.Color.BLACK);
		knight.setType(Piece.Type.KNIGHT);
		knight.setCurrentSpace(board.getSpace(1, 3));
		knight.setBoard(board);
		
		assertEquals(knight.getColor(), Piece.Color.BLACK);
		assertEquals(knight.getType(), Piece.Type.KNIGHT);
		assertEquals(knight.getCurrentSpace().getX(), 1);
		assertEquals(knight.getCurrentSpace().getY(), 3);
		assertEquals(knight.getBoard(), board);
	}
	
	@Test
	public void testGetPossibleMoves() {
		List<Space> correctSpaces = new ArrayList<>();
		correctSpaces.add(board.getSpace(1, 3));
		correctSpaces.add(board.getSpace(3, 3));
		
		List<Space> spacesFromCall = knight.findPossibleMoves();
		
		assertEquals(correctSpaces, spacesFromCall);
	}
}
