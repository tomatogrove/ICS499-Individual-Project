package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import com.team6.model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightUnitTest {

	Board board;
	Piece knight;
	
	@BeforeEach
	public void setup() {
		board = new Board();
		knight = board.findSpace(2, 1).getPiece();
	}
	
	@Test
	public void testSettersAndGetters() {
		knight.setColor(Color.BLACK);
		knight.setType(Type.KNIGHT);
		knight.setCurrentSpace(board.findSpace(1, 3));
		knight.setBoard(board);
		
		assertEquals(knight.getColor(), Color.BLACK);
		assertEquals(knight.getType(), Type.KNIGHT);
		assertEquals(knight.getCurrentSpace().getX(), 1);
		assertEquals(knight.getCurrentSpace().getY(), 3);
		assertEquals(knight.getBoard(), board);
	}
	
	@Test
	public void testGetPossibleMoves() {
		List<Space> correctSpaces = new ArrayList<>();
		correctSpaces.add(board.findSpace(1, 3));
		correctSpaces.add(board.findSpace(3, 3));
		
		List<Space> spacesFromCall = knight.findPossibleMoves();
		
		assertEquals(correctSpaces, spacesFromCall);
	}
}
