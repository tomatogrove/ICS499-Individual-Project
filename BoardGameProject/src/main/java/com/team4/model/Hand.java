package com.team4.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Hand {

	@Id
	@GeneratedValue
	private Long handID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Card> cards;
	
	public Hand() {
		
	}
	
	public Hand(List<Card> cards) {
		this.cards = cards;
	}
	
	public Long getHandID() { return handID; }
	public void setHandID(Long handID) { this.handID = handID; }
	
	public List<Card> getCards() { return cards; }
	public void setCards(List<Card> cards) { this.cards = cards; }

}
