package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Queen extends Piece {

    public Queen() {
    	
    }

    public Queen(Color color, Space currentSpace) {
        super(color, Piece.Type.QUEEN, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }

}
