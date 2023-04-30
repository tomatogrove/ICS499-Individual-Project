package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopUnitTest {
    Board board;
    Piece bishop;

    @BeforeEach
    public void setup() {
        board = new Board();
        bishop = board.findSpace(3, 1).getPiece();
    }

    @Test
    public void testSettersAndGetters() {
        bishop.setColor(Piece.Color.BLACK);
        bishop.setType(Piece.Type.BISHOP);
        bishop.setCurrentSpace(board.findSpace(1, 3));
        bishop.setBoard(board);

        assertEquals(bishop.getColor(), Piece.Color.BLACK);
        assertEquals(bishop.getType(), Piece.Type.BISHOP);
        assertEquals(bishop.getCurrentSpace().getX(), 1);
        assertEquals(bishop.getCurrentSpace().getY(), 3);
        assertEquals(bishop.getBoard(), board);
    }

    @Test
    public void testGetPossibleMovesCannotMove() {
        List<Space> correctSpaces = new ArrayList<>();

        List<Space> spacesFromCall = bishop.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }

    @Test
    public void testGetPossibleMoves() {
        bishop.getCurrentSpace().setPiece(null);
        Space newSpace = board.findSpace(3, 3);
        newSpace.setPiece(bishop);
        bishop.setCurrentSpace(newSpace);

        List<Space> correctSpaces = new ArrayList<>();
        correctSpaces.add(board.findSpace(4, 4));
        correctSpaces.add(board.findSpace(5, 5));
        correctSpaces.add(board.findSpace(6, 6));
        correctSpaces.add(board.findSpace(7, 7));
        correctSpaces.add(board.findSpace(2, 4));
        correctSpaces.add(board.findSpace(1, 5));

        List<Space> spacesFromCall = bishop.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }
}
