package com.ics499.team4.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.team4.model.Chess;
import com.team4.model.Game;
import com.team4.model.Player;

public class PlayerUnitTest {
	
	Player player;
	
	List<Game> games;
	
	@BeforeEach
	public void setup() {
		games = new ArrayList<>();
		player = new Player();
		
		
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		Game game;
		for (int i = 0; i < 4; i++) {
			game = new Chess(players);
			game.setStatus(Game.Status.ACTIVE);
			games.add(game);
			
		}

		player.setGames(games);
	}
	
	@Test
	public void testSettersAndGetters() {
		player.setUsername("testUser");
		player.setPassword("testPass");
		player.setGuest(true);
		player.setEmail("testUser@email.com");
		
		assertEquals(games, player.getGames());
		assertEquals("testUser", player.getUsername());
		assertEquals("testPass", player.getPassword());
		assertEquals("testUser@email.com", player.getEmail());
		assertTrue(player.isGuest());
	}
	
	@Test
	public void testFindGamesByStatus() {
		player.getGames().get(0).setStatus(Game.Status.WON);
		player.getGames().get(1).setStatus(Game.Status.LOST);
		
		List<Game> activeGames = new ArrayList<>();
		activeGames.add(games.get(2));
		activeGames.add(games.get(3));
		
		List<Game> findResults = player.findGamesByStatus(Game.Status.ACTIVE);
		
		assertEquals(activeGames, findResults);
	}
	
}
