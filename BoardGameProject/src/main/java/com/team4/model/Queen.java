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
		Board board = getBoard();
		int x = getCurrentSpace().getX();
		int y = getCurrentSpace().getY();
		
        return possibleMoves;
    }

}
