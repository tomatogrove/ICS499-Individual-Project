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
        return getPossibleDiagonalMoves();
    }

	private List<Space> getPossibleDiagonalMoves() {
		// TODO Auto-generated method stub
		return null;
	}
    
}

