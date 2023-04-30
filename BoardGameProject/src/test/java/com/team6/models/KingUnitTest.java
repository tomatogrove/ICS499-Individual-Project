package com.team6.models;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import com.team6.model.pieces.King;
import com.team6.model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingUnitTest {

    Board board;
    Piece king;

    @BeforeEach
    public void setup() {
        board = new Board();
        king = board.findSpace(5, 1).getPiece();
    }

    @Test
    public void testSettersAndGetters() {
        king.setColor(Color.BLACK);
        king.setType(Type.KING);
        king.setCurrentSpace(board.findSpace(1, 3));
        king.setBoard(board);
        ((King) king).setHasMoved(true);

        assertEquals(king.getColor(), Color.BLACK);
        assertEquals(king.getType(), Type.KING);
        assertEquals(king.getCurrentSpace().getX(), 1);
        assertEquals(king.getCurrentSpace().getY(), 3);
        assertEquals(king.getBoard(), board);
        assertTrue(((King) king).getHasMoved());
    }

    @Test
    public void testGetPossibleMovesCannotMove() {
        List<Space> correctSpaces = new ArrayList<>();

        List<Space> spacesFromCall = king.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }

    @Test
    public void testGetPossibleMovesCanMove() {
        // remove the pawns in front of the king
        removePawns();

        List<Space> correctSpaces = new ArrayList<>();
        correctSpaces.add(board.findSpace(4, 2));
        correctSpaces.add(board.findSpace(5, 2));
        correctSpaces.add(board.findSpace(6, 2));

        List<Space> spacesFromCall = king.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }

    @Test
    public void testGetPossibleMovesCanMoveCheck() {
        // remove the pawns in front of the king and move to check for space(5, 2)
        removePawns();
        Piece bishop = board.findSpace(3, 8).getPiece();
        bishop.setCurrentSpace(board.findSpace(1, 6));
        board.findSpace(3, 8).setPiece(null);

        List<Space> correctSpaces = new ArrayList<>();
        correctSpaces.add(board.findSpace(4, 2));
        correctSpaces.add(board.findSpace(6, 2));

        List<Space> spacesFromCall = king.findPossibleMoves();

        assertEquals(correctSpaces, spacesFromCall);
    }

    private void removePawns() {
        Piece pawn = board.findSpace(4, 2).getPiece();
        pawn.setCurrentSpace(null);
        board.findSpace(4, 2).setPiece(null);

        pawn = board.findSpace(5, 2).getPiece();
        pawn.setCurrentSpace(null);
        board.findSpace(5, 2).setPiece(null);

        pawn = board.findSpace(6, 2).getPiece();
        pawn.setCurrentSpace(null);
        board.findSpace(6, 2).setPiece(null);
    }
}
