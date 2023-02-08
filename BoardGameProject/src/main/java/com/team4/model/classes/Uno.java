package com.team4.model.classes;

import com.backend.abstrct.Rule;
import com.team4.model.abstrct.Game;

public class Uno extends Game {
	
	private Card[] cards;
	private Rule[] rules;
	
	public Uno(Card[] cards, Rule[] rules) {
		this.cards = cards;
		this.rules = rules;
	}
	
	
	public Card[] getCards() {
		return cards;
	}
	
	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	public Rule[] getRules() {
		return rules;
	}
	
	public void setRules(Rule[] rules) {
		this.rules = rules;
	}
	

}
