package com.team4.model.classes;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Card {
	
	@Id
	@GeneratedValue
	private Long cardID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Deck deck;
	
	private String color;
	private int type;
	

	// for Hibernate
	public Card() {
		
	}
	
	public Card(String color, int type) {
		this.color = color;
		this.type = type;
	}
	
	public Long getCardID() {
		return cardID;
	}
	
	public void setCardID(Long cardID) {
		this.cardID = cardID;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
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
