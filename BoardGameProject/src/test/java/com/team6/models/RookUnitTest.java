package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import com.team6.model.pieces.Piece;
import com.team6.model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookUnitTest {

    Board board;
    Piece rook;

    @BeforeEach
    public void setup() {
        board = new Board();
        rook = board.findSpace(1, 1).getPiece();
    }

    @Test
    public void testSettersAndGetters() {
        rook.setColor(Color.BLACK);
        rook.setType(Type.ROOK);
        rook.setCurrentSpace(board.findSpace(1, 3));
        rook.setBoard(board);
        ((Rook) rook).setHasMoved(true);

        assertEquals(rook.getColor(), Color.BLACK);
        assertEquals(rook.getType(), Type.ROOK);
        assertEquals(rook.getCurrentSpace().getX(), 1);
        assertEquals(rook.getCurrentSpace().getY(), 3);
        assertEquals(rook.getBoard(), board);
        assertTrue(((Rook) rook).getHasMoved());
    }

    @Test
    public void testGetPossibleMovesCannotMove() {
        List<Space> correctSpaces = new ArrayList<>();

        List<Space> spacesFromCall = rook.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }
    @Test
    public void testGetPossibleMovesCanMove() {
        board.findSpace(1, 2).setPiece(null);
        List<Space> correctSpaces = new ArrayList<>();
        correctSpaces.add(board.findSpace(1, 2));
        correctSpaces.add(board.findSpace(1, 3));
        correctSpaces.add(board.findSpace(1, 4));
        correctSpaces.add(board.findSpace(1, 5));
        correctSpaces.add(board.findSpace(1, 6));
        correctSpaces.add(board.findSpace(1, 7));

        List<Space> spacesFromCall = rook.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }

}
