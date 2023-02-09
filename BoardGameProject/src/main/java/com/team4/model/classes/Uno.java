package com.team4.model.classes;

import java.util.List;

import com.team4.model.abstrct.Game;

public class Uno extends Game {
	
	private List<Card> cards;
	private List<Rule> rules;
	
	public Uno(List<Card> cards, List<Rule> rules) {
		this.cards = cards;
		this.rules = rules;
	}
	
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	

}
