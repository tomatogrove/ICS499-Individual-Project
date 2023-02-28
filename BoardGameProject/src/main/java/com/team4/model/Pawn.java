package com.team4.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;


@Entity
public class Pawn extends Piece {
	
	private Space currentSpace;
	
	public Pawn() {
		
	}
	
    public Pawn(Color color, Space currentSpace) {
        super(color, Piece.Type.PAWN ,currentSpace);
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