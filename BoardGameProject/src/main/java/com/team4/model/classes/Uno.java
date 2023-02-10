package com.team4.model.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Game;

public class Uno extends Game {
	
	@Id
	@GeneratedValue
	private Long unoID;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Deck> decks;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Rule> rules;
	
	public Uno() {
		this.decks.add(new Deck(true));
		this.decks.add(new Deck(false));
	}
	
	public Uno(List<Rule> rules) {
		this.rules = rules;
		this.decks.add(new Deck(true));
		this.decks.add(new Deck(false));
	}
	
	public Uno(List<Deck> decks, List<Rule> rules) {
		this.decks = decks;
		this.rules = rules;
	}
	
	
	public List<Deck> getDecks() {
		return decks;
	}
	
	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	

}
