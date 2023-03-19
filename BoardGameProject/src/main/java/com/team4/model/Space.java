package com.team4.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Space {
	
	@Id
	@GeneratedValue
	private long spaceID;
	
	private int x;
	private int y;
	private boolean occupied;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Piece piece;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Board board;
	
	public Space() {
		
	}
	
	public Space(int x, int y) {
		this.x = x;
		this.y = y;
		occupied = false;
	}

	public boolean getOccupied() {
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
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getSpaceID() {
		return spaceID;
	}

	public void setSpaceID(long spaceID) {
		this.spaceID = spaceID;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	
}
