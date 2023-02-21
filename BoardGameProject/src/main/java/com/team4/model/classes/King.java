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
public class King extends Piece {
	
	
	@Id
	@GeneratedValue
	private Long kingID;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private List<Space> Space;
	
    public King(Color color, Space currentSpace) {
        super(color, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        
        return possibleMoves;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
