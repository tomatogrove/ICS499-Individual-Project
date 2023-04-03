package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Bishop extends Piece {
	
    public Bishop() {
    	super();
    }

    public Bishop(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.BISHOP, currentSpace, board);
    }

    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();

        possibleMoves.addAll(this.getPossibleMovesInDirection( 1,  1));
        possibleMoves.addAll(this.getPossibleMovesInDirection( 1, -1));
        possibleMoves.addAll(this.getPossibleMovesInDirection(-1,  1));
        possibleMoves.addAll(this.getPossibleMovesInDirection(-1, -1));

        return possibleMoves;
    }

    /**
     *  Returns possible moves in one diagonal given the direction polarities.
     * @param xPolarity -1 or 1
     * @param yPolarity -1 or 1
     * @return  possible moves in one diagonal
     */
    private List<Space> getPossibleMovesInDirection(int xPolarity, int yPolarity){
        List<Space> possibleMoves = new ArrayList<>();

        // Bad Input
        if(Math.abs(xPolarity * yPolarity) != 1){
            return possibleMoves;
        }

        Board board = getBoard();
        int x = this.getCurrentSpace().getX();
        int y = this.getCurrentSpace().getY();

        Space space = this.getCurrentSpace();

        int i = 1;
        while(space != null){
            if(!space.getOccupied()) {
                possibleMoves.add(space);
            } 
            
            if (space.getOccupied() && !space.getPiece().getColor().equals(getColor())) {
            	possibleMoves.add(space);
            	break;
            }
            space = board.findSpace(x + (xPolarity * i), y + (yPolarity * i));
            i++;
        }

        return possibleMoves;
    }

}

