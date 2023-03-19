package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;


@Entity
public class King extends Piece {
	
	public King() {
		
	}
	
    public King(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.KING ,currentSpace, board);
    }

    
    // keep castling in mind and preventing check
    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
        return possibleMoves;
    }
}
