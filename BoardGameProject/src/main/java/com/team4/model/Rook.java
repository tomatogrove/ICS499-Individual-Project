package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Rook extends Piece {
	
	private boolean hasMoved = false;
    
    public Rook() {
    	
    }

    public Rook(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.ROOK,  currentSpace, board);
    }

    // keep castling in mind
    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();

        possibleMoves.addAll(this.getPossibleMovesInDirection( 1, 0));
        possibleMoves.addAll(this.getPossibleMovesInDirection(-1, 0));
        possibleMoves.addAll(this.getPossibleMovesInDirection( 0, 1));
        possibleMoves.addAll(this.getPossibleMovesInDirection( 0,-1));

        return possibleMoves;
    }

    /**
     *  Returns possible moves in one line given the direction polarities.
     * @param xPolarity -1, 1 or 0
     * @param yPolarity -1, 1 or 0
     * @return  possible moves in one line
     */
    private List<Space> getPossibleMovesInDirection(int xPolarity, int yPolarity){
        List<Space> possibleMoves = new ArrayList<>();

        // Bad Input
//        if(Math.abs(xPolarity * yPolarity) != 1 || (xPolarity * yPolarity) != 0) {
//            return possibleMoves;
//        }

        Board board = getBoard();
        int x = this.getCurrentSpace().getX();
        int y = this.getCurrentSpace().getY();

        Space space = null;

        int i = 1;
        do{
        	if(space != null) {
	            if(!space.getOccupied()) {
	                possibleMoves.add(space);
	            } else if (space.getOccupied() && !space.getPiece().getColor().equals(getColor())) {
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
        } while(space != null);

        return possibleMoves;
    }

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
