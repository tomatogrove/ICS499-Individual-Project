package com.team4.model.classes;

public class Card {
	
	private String color;
	private int type;
	
	
	public Card(String color, int type) {
		this.color = color;
		this.type = type;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

}
