package com.team6.model.pieces;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bishop extends Piece {

    public Bishop() {
        super();
    }

    public Bishop(Color color, Space currentSpace, Board board) {
        super(color, Type.BISHOP, currentSpace, board);
    }

    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();

        possibleMoves.addAll(this.getPossibleMovesInDirection(1, 1));
        possibleMoves.addAll(this.getPossibleMovesInDirection(1, -1));
        possibleMoves.addAll(this.getPossibleMovesInDirection(-1, 1));
        possibleMoves.addAll(this.getPossibleMovesInDirection(-1, -1));

        return possibleMoves;
    }

    /**
     * Returns possible moves in one diagonal given the direction polarities.
     *
     * @param xPolarity -1 or 1
     * @param yPolarity -1 or 1
     * @return possible moves in one diagonal
     */
    private List<Space> getPossibleMovesInDirection(int xPolarity, int yPolarity) {
        List<Space> possibleMoves = new ArrayList<>();

        // Bad Input
        if (Math.abs(xPolarity * yPolarity) != 1) {
            return possibleMoves;
        }

        Board board = getBoard();
        int x = this.getCurrentSpace().getX();
        int y = this.getCurrentSpace().getY();

        Space space = null;

        int i = 1;
        do {
            if (space != null) {
                if (!space.isOccupied()) {
                    possibleMoves.add(space);
                } else if (space.isOccupied() && !space.getPiece().getColor().equals(getColor())) {
                    possibleMoves.add(space);
                    space = null;
                    break;
                } else {
                    space = null;
                    break;
                }
            }
            space = board.findSpace(x + (xPolarity * i), y + (yPolarity * i));
            i++;
        } while (space != null);

        return possibleMoves;
    }

}

