package com.team4.model.classes.uno;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.team4.model.abstrct.Game;
import com.team4.model.classes.Player;
import com.team4.model.classes.Rule;

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
