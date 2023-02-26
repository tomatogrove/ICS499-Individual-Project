package com.team4.model.classes.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.team4.model.abstrct.Piece;
import com.team4.model.classes.chess.Space;

@Entity
public class Bishop extends Piece {

	
    public Bishop() {
    	super();
    }

    public Bishop(Color color, Space currentSpace) {
        super(color, Piece.Type.BISHOP, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }
    
    @Id
    @GeneratedValue
    @Override
    public Long getPieceID() {
    	return this.pieceID;
    }
    
    public void setPieceID(Long pieceID) {
    	this.pieceID = pieceID;
    }
    
}

