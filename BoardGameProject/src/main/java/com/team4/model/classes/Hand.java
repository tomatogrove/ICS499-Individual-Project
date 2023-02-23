package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import javax.persistence.*;

@Entity
@Table(name = "hand")
public class Hand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	private List<Card> cards = new ArrayList<>();

	public Hand() {
		this.cards = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void removeCard(Card card) {
		this.cards.remove(card);
	}

	public boolean hasCard(Card card) {
		return this.cards.contains(card);
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int size() {
		return this.cards.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Hand [");
		for (Card card : this.cards) {
			sb.append(card.toString());
			sb.append(", ");
		}
		if (!this.cards.isEmpty()) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("]");
		return sb.toString();
	}
}
