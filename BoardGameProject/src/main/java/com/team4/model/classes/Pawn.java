package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

import com.team4.model.abstrct.Piece;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Pawn extends Piece {
	
	
	@Id
	@GeneratedValue
	private Long pawnID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private List<Space> Space;
	
    public Pawn(Color color, Space currentSpace) {
        super(color, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        
        return possibleMoves;
    } 
    
    public Long getPawnID() {
        return pawnID;
    }

    public void setPawnID(Long pawnID) {
        this.pawnID = pawnID;
    }
}


