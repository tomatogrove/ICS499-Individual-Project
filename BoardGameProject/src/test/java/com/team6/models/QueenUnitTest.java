package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenUnitTest {
    
    Board board;
    Piece queen;

    @BeforeEach
    public void setup() {
        board = new Board();
        queen = board.findSpace(4, 1).getPiece();
    }

    @Test
    public void testSettersAndGetters() {
        queen.setColor(Piece.Color.BLACK);
        queen.setType(Piece.Type.QUEEN);
        queen.setCurrentSpace(board.findSpace(4, 3));
        queen.setBoard(board);

        assertEquals(queen.getColor(), Piece.Color.BLACK);
        assertEquals(queen.getType(), Piece.Type.QUEEN);
        assertEquals(queen.getCurrentSpace().getX(), 4);
        assertEquals(queen.getCurrentSpace().getY(), 3);
        assertEquals(queen.getBoard(), board);
    }

    @Test
    public void testGetPossibleMovesCannotMove() {
        List<Space> correctSpaces = new ArrayList<>();

        List<Space> spacesFromCall = queen.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }

    @Test
    public void testGetPossibleMovesCanMove() {
        board.findSpace(4, 2).setPiece(null);
        List<Space> correctSpaces = new ArrayList<>();
        correctSpaces.add(board.findSpace(4, 2));
        correctSpaces.add(board.findSpace(4, 3));
        correctSpaces.add(board.findSpace(4, 4));
        correctSpaces.add(board.findSpace(4, 5));
        correctSpaces.add(board.findSpace(4, 6));
        correctSpaces.add(board.findSpace(4, 7));

        List<Space> spacesFromCall = queen.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }
}
