package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;


@Entity
public class King extends Piece {
	
	private boolean hasMoved;
	
	public King() {
		hasMoved = false;
		
	}
	
    public King(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.KING ,currentSpace, board);
    }

    
    // keep castling in mind and preventing check
    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
		
		
		// if the position 1 space ahead of the king is not occupied, add it as a possible move
		if (!board.getSpace(x, y + 1).getOccupied()) {
			possibleMoves.add(board.getSpace(x, y + 1));
		}
		
		// if the position 1 space to the right of the king is not occupied, add it as a possible move
		if (!board.getSpace(x + 1, y).getOccupied()) {
			possibleMoves.add(board.getSpace(x + 1, y));
		}
		
		// if the position 1 space behind the king is not occupied, add it as a possible move
		if (!board.getSpace(x, y - 1).getOccupied()) {
			possibleMoves.add(board.getSpace(x, y - 1));
		}
		
		// if the position 1 space to the left of the king is not occupied, add it as a possible move
		if (!board.getSpace(x - 1, y).getOccupied()) {
			possibleMoves.add(board.getSpace(x - 1, y));
		}
		
		
		// if the position 1 space northwest of the king is not occupied, add it as a possible move
		if (!board.getSpace(x - 1, y + 1).getOccupied()) {
			possibleMoves.add(board.getSpace(x - 1, y + 1));
		}
		
		// if the position 1 space northeast of the king is not occupied, add it as a possible move
		if (!board.getSpace(x + 1, y + 1).getOccupied()) {
			possibleMoves.add(board.getSpace(x + 1, y + 1));
		}
		
		// if the position 1 space southwest of the king is not occupied, add it as a possible move
		if (!board.getSpace(x - 1, y - 1).getOccupied()) {
			possibleMoves.add(board.getSpace(x - 1, y - 1));
		}
		
		// if the position 1 space southeast of the king is not occupied, add it as a possible move
		if (!board.getSpace(x + 1, y - 1).getOccupied()) {
			possibleMoves.add(board.getSpace(x + 1, y - 1));
		}
		
		int[] possibleValues = {-1, 0, 1};
        for (int i : possibleValues) {
        	for (int j : possibleValues) {
        		Space space = board.getSpace(x + i, y + j);
			// checking if the space exists on the board before adding it to the possible moves
				if (space != null) {
				// if the space is occupied by an enemy color, then add it as a possible move (capture)
					if (space.getOccupied() && !space.getPiece().getColor().equals(getColor())) {
						possibleMoves.add(space);
					
				//if the space is not occupied, add it as a possible move
					} else if (space.getX() != x && !space.getOccupied()) {
						possibleMoves.add(space);
					}
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
