package com.ics499.team4.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.team4.model.Chess;
import com.team4.model.Player;

public class PlayerUnitTest {
	
	Player player;
	
	List<Chess> chessList;
	
	@BeforeEach
	public void setup() {
		chessList = new ArrayList<>();
		player = new Player();
		
		
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		Chess chess;
		for (int i = 0; i < 4; i++) {
			chess = new Chess(players);
			chess.setStatus(Chess.Status.ACTIVE);
			chessList.add(chess);
			
		}

		player.setChessList(chessList);
	}
	
	@Test
	public void testSettersAndGetters() {
		player.setUsername("testUser");
		player.setPassword("testPass");
		player.setGuest(true);
		player.setEmail("testUser@email.com");
		
		assertEquals(chessList, player.getChessList());
		assertEquals("testUser", player.getUsername());
		assertEquals("testPass", player.getPassword());
		assertEquals("testUser@email.com", player.getEmail());
		assertTrue(player.isGuest());
	}
	
	@Test
	public void testFindChessByStatus() {
		player.getChessList().get(0).setStatus(Chess.Status.WON);
		player.getChessList().get(1).setStatus(Chess.Status.LOST);
		
		List<Chess> activeChess = new ArrayList<>();
		activeChess.add(chessList.get(2));
		activeChess.add(chessList.get(3));
		
		List<Chess> findResults = player.findChessListByStatus(Chess.Status.ACTIVE);
		
		assertEquals(activeChess, findResults);
	}
	
}
