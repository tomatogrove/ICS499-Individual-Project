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
//    	findPossibleLinearMoves();
    	return null;
    }

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
