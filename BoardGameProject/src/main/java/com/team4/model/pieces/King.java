package com.team4.model.pieces;

import com.team4.model.Board;
import com.team4.model.Space;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class King extends Piece {

    private boolean hasMoved;

    public King() {
        setHasMoved(false);
    }

    public King(Color color, Space currentSpace, Board board) {
        super(color, Type.KING ,currentSpace, board);
    }

    
//    TODO castling
//    TODO infinite loop with enemy king
//    TODO pawn diagonals
    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();

        Board board = getBoard();
        int x = getCurrentSpace().getX();
        int y = getCurrentSpace().getY();

        Color enemyColor = getColor() == Color.BLACK ? Color.WHITE : Color.BLACK;
        List<Piece> enemyPieces = board.findPiecesByColor(enemyColor);

        Set<Space> enemyMoves = new HashSet<>();
        for (Piece enemyPiece : enemyPieces) {
            if (!enemyPiece.getType().equals(Type.KING)) {
                if (!enemyPiece.getType().equals(Type.PAWN)) {
                    List<Space> enemyPossibleMoves = enemyPiece.findPossibleMoves();
                    if (enemyPossibleMoves.size() > 0) {
                        enemyMoves.addAll(enemyPossibleMoves);
                    }
                }
            }
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) { continue; }

                Space space = board.findSpace(x + i, y + j);
                if (space == null || enemyMoves.contains(space) || enemyPawnCanAttack(space)) { continue; }

                if (!space.isOccupied() || (space.isOccupied() && space.getPiece().getColor().equals(enemyColor))) {
                    possibleMoves.add(space);
                }
            }
        }

        return possibleMoves;
    }

    private boolean enemyPawnCanAttack(Space space) {
        int yDirection = getColor().equals(Color.WHITE) ? 1 : -1;

        int spaceX = space.getX();
        int spaceY = space.getY();

        Space spaceDiagonal = getBoard().findSpace(spaceX + 1, spaceY + yDirection);
        if (spaceDiagonal != null && spaceDiagonal.isOccupied() && !spaceDiagonal.getPiece().getColor().equals(getColor()) && spaceDiagonal.getPiece().getType().equals(Type.PAWN)) {
            return true;
        }
        spaceDiagonal = getBoard().findSpace(spaceX - 1, spaceY + yDirection);
        if (spaceDiagonal != null && spaceDiagonal.isOccupied() && !spaceDiagonal.getPiece().getColor().equals(getColor()) && spaceDiagonal.getPiece().getType().equals(Type.PAWN)) {
            return true;
        }

        return false;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
