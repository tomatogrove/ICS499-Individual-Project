package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Knight extends Piece {

	public Knight() {
		
	}
	
	public Knight(Color color, Space currentSpace) {
        super(color, Piece.Type.KNIGHT, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }
}