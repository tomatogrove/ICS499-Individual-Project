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
		Board board = getBoard();
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
		for (int i = 0; i < 4; i++) {
			boolean blocked = false;
			
			
		}
		
		
		
        return possibleMoves;
    }
    
}

