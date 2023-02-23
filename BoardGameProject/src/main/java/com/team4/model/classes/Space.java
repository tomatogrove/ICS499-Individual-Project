package com.team4.model.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.team4.model.abstrct.Piece;

@Entity
public class Space {
	
	@Id
	@GeneratedValue
	private long spaceID;
	
	private int x;
	private int y;
	private boolean occupied;
	
	@OneToOne(targetEntity = Piece.class)
	private Piece piece;
	
	public Space() {
		
	}
	
	public Space(int x, int y) {
		this.x = x;
		this.y = y;
		occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
