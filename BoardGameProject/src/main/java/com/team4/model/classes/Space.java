package com.team4.model.classes;

public class Space {
	private int x;
	private int y;
	private boolean occupied;
	
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
