package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import com.team4.model.Player;
import com.team4.model.Rule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Uno extends Game {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "GamePlayer",
			joinColumns = @JoinColumn(name = "gameID"),
			inverseJoinColumns = @JoinColumn(name = "playerID"))
	private List<Player> players;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Deck> decks;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Rule> rules;
	
	public Uno() {
		this.rules = new ArrayList<>();
		this.decks = new ArrayList<>();
		this.decks.add(new Deck(true));
		this.decks.add(new Deck(false));
	}
	
	public Uno(List<Rule> rules) {
		this.rules = rules;
		this.decks = new ArrayList<>();
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