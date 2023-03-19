package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Knight extends Piece {

	public Knight() {
		
	}
	
	public Knight(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.KNIGHT, currentSpace, board);
    }

    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        Board board = getBoard();
        int x = getCurrentSpace().getX();
        int y = getCurrentSpace().getY();
        
        // knights move in L shapes, so they always move 2 spaces, and then 1 space perpendicularly
        int[] loopValues = new int[]{-2, -1, 1, 2};
        for (int i : loopValues) {
        	for (int j : loopValues) {
        		
        		// blocks diagonal spaces from being added to the possible moves
        		if (Math.abs(i) != Math.abs(j)) {
        			Space space = board.getSpace(x + i, y + j);
        			
        			// want to make sure the space exists on the board
        			if (space != null) {
        				
        				// if the space is occupied, the knight can only move to it to capture
        				if (space.getOccupied() && !space.getPiece().getColor().equals(getColor())) {
        					possibleMoves.add(space);
        					
        				// if unoccupied then its free to be claimed
        				} else if (!space.getOccupied()) {
        					possibleMoves.add(space);
        				}
        			}
        		}
        	}
        }
        
        
        return possibleMoves;
    }
}