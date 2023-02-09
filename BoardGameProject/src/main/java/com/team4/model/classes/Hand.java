package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Card;

@Entity
public class Hand {

	@Id
	@GeneratedValue
	private Long handID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "Hand",
			joinColumns = @JoinColumn(name = "handID"),
			inverseJoinColumns = @JoinColumn(name = "cardID"))
	private List<Card> cards;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	
	public Hand(List<Card> cards) {
		this.cards = cards;
	}
	
	public Long getHandID() { return handID; }
	public void setHandID(Long handID) { this.handID = handID; }
	
	public List<Card> getCards() { return cards; }
	public void setCards(List<Card> cards) { this.cards = cards; }

}
