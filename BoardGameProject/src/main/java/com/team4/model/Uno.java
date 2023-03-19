package com.team4.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Deck> decks;
	
	public Uno() {
	}
	
	@PostConstruct
	private void postConstruct() {
		this.decks = new ArrayList<>();
		this.decks.add(new Deck(true));
		this.decks.add(new Deck(false));
	}
	
	
	public Uno(List<Deck> decks) {
		this.decks = decks;
	}
	
	
	public List<Deck> getDecks() {
		return decks;
	}
	
	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	

}
