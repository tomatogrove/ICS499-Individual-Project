package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Rook extends Piece {
    
    public Rook() {
    	
    }

    public Rook(Color color, Space currentSpace, Board board) {
        super(color, Piece.Type.ROOK,  currentSpace, board);
    }

    // keep castling in mind
    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
		Board board = getBoard();
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
        return possibleMoves;
    }

}
