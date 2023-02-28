package com.team4.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Card {
	
	@Id
	@GeneratedValue
	private Long cardID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Deck deck;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Hand hand;
	

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
	
	public Hand getHand() {
		return hand;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
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