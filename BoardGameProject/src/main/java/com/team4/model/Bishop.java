package com.team4.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;


@Entity
public class Bishop extends Piece {
	
	private Space currentSpace;
	
	public Bishop() {
		
	}
	
    public Bishop(Color color, Space currentSpace) {
        super(color, Piece.Type.BISHOP ,currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }
    
    public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}
}