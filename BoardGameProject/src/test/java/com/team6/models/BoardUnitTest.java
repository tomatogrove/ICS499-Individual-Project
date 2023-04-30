package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Chess;
import com.team6.model.Space;
import com.team6.model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardUnitTest {

    Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void testSettersAndGetters() {
        Chess newChess = new Chess();
        board.setChess(newChess);
        board.setSpaces(new ArrayList<>());
        board.setPieces(new ArrayList<>());

        assertEquals(board.getChess(), newChess);
        assertEquals(board.getSpaces(), new ArrayList<>());
        assertEquals(board.getPieces(), new ArrayList<>());
    }

    @Test
    public void testFindSpaceExists() {
        Space space = board.findSpace(2, 1);
        assertTrue(space.isOccupied());
        assertEquals(space.getX(), 2);
        assertEquals(space.getY(), 1);
        assertEquals(space.getPiece().getType(), Piece.Type.KNIGHT);
        assertEquals(space.getPiece().getColor(), Piece.Color.WHITE);
    }

    @Test
    public void testFindSpaceNotExists() {
        Space space = board.findSpace(1, 9);
        assertNull(space);

        space = board.findSpace(-3, 3);
        assertNull(space);
    }

    @Test
    public void testFindPiecesByColor() {
        List<Piece> pieces = board.findPiecesByColor(Piece.Color.BLACK);

        assertEquals(pieces.size(), 16);
        for (Piece piece : pieces) {
            assertEquals(piece.getColor(), Piece.Color.BLACK);
        }
    }

    @Test
    public void testFindPieces() {
        List<Piece> pieces = board.findPieces(Piece.Color.WHITE, Piece.Type.PAWN);

        assertEquals(pieces.size(), 8);
        for (Piece piece : pieces) {
            assertEquals(piece.getColor(), Piece.Color.WHITE);
            assertEquals(piece.getType(), Piece.Type.PAWN);
        }
    }
}
