package com.team6.model.pieces;

import com.team6.model.Board;
import com.team6.model.Space;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Pawn extends Piece {

    private boolean hasMoved;

    public Pawn() {
        hasMoved = false;
    }

    public Pawn(Color color, Space currentSpace, Board board) {
        super(color, Type.PAWN, currentSpace, board);
    }

    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves;

        // movement of pawns is opposite (regarding vertical movement) for each color... white's logic first
        if (getColor().equals(Color.WHITE)) {
            possibleMoves = findPossibleMovesWithPolarity(1);
        } else {
            possibleMoves = findPossibleMovesWithPolarity(-1);
        }

        return possibleMoves;
    }

    private List<Space> findPossibleMovesWithPolarity(int yPolarity) {
        List<Space> possibleMoves = new ArrayList<>();
        Board board = getBoard();
        int x = getCurrentSpace().getX();
        int y = getCurrentSpace().getY();

        // if it is the first move, and the positions one and two spaces ahead are not occupied, add it as a possible move
        if (!hasMoved && !board.findSpace(x, y + (yPolarity)).isOccupied() && !board.findSpace(x, y + (yPolarity * 2)).isOccupied()) {
            possibleMoves.add(board.findSpace(x, y + (yPolarity * 2)));
        }

        for (int i = - 1; i < 2; i++) {
            Space space = board.findSpace(x + i, y + yPolarity);
            // ensure that the space exists on the board before adding it to the possible moves
            if (space != null) {
                // if the space is a diagonal, and it is occupied by an enemy color, then add it as a possible move (capture)
                if (space.getX() != x && space.isOccupied() && !space.getPiece().getColor().equals(getColor())) {
                    possibleMoves.add(space);

                    //otherwise if the space is not occupied it is viable
                } else if (space.getX() == x && !space.isOccupied()) {
                    possibleMoves.add(space);
                }
            }
        }

        return possibleMoves;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

}
