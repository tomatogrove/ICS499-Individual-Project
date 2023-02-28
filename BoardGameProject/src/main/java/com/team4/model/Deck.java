package com.team4.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Deck {
	
	@Id
	@GeneratedValue
	private Long deckID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Uno uno;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Card> cards;
	
	private boolean isWithdraw;
	


	// for Hibernate
	public Deck() {

	}
	

	public Deck(boolean isWithdraw) { 
		cards = new ArrayList<Card>();
		this.isWithdraw = isWithdraw;
		
		if (this.isWithdraw) {
			setInitialCards();
		}
	}

	public List<Card> getCards() { return cards; }
	public void setCards(List<Card> cards) { this.cards = cards; }

	public Long getDeckID() { return deckID; }
	public void setDeckID(Long deckID) { this.deckID = deckID; }
	
	public Uno getUno() { return uno; }
	public void setUno(Uno uno) { this.uno = uno; }
	
	public boolean isWithdraw() { return isWithdraw; }
	public void setWithdraw(boolean isWithdraw) { this.isWithdraw = isWithdraw; }

	/** 
	 * for use when deck is withdrawn from
	 * 
	 * @return the top card of the deck
	 */
	public Card getTopCard() { return cards.remove(cards.size() - 1); }
	
	/** 
	 * for use when deck is a discard pile
	 * 
	 * @param card to add to the deck
	 */
	public void addCard(Card card) { cards.add(card); }
	
	
	public void shuffle() { Collections.shuffle(cards); }
	
	
	// initializes the 108 cards needed for the initial Uno deck.
	private void setInitialCards() {
		String[] colors = {"red", "blue", "green", "yellow"};
		
		for (int i = 0; i < colors.length; i++) {			

			// makes 1 zero card for each color
			cards.add(new Card(colors[i], 0));

			// makes 2 cards of each type 1-9, draw 2, reverse, and skip for each color
			for (int j = 1; j < 13; j++) {
				cards.add(new Card(colors[i], j));
				cards.add(new Card(colors[i], j));
			}
		}
		
		// makes 8 wild cards, 4 of which are draw 4 wild cards
		for (int i = 13; i < 15; i++) {
			for (int j = 0; j < 4; j++) {
				cards.add(new Card("multi", i));
			}
		}
		
		
	}

}