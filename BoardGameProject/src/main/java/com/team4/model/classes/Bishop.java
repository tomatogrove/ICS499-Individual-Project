package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.team4.model.abstrct.Piece;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bishop extends Piece {
    
    @Id
    @GeneratedValue
    private Long id;
    
    public Bishop(Color color, Space currentSpace) {
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
