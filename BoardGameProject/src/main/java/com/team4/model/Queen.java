package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Queen extends Piece {

    public Queen() {
    	
    }

    public Queen(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.QUEEN, currentSpace, board);
    }

    @Override
    public List<Space> findPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
		possibleMoves.addAll(getPossibleDiagonalMoves());
		possibleMoves.addAll(getPossibleLinearMoves());
        return possibleMoves;
    }

}
