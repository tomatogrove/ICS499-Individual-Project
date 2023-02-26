package com.team4.model.classes.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

import com.team4.model.abstrct.Piece;
import com.team4.model.classes.chess.Space;

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