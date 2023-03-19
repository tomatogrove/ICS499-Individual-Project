package com.ics499.team4.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.team4.model.Player;
import com.team4.repositories.PlayerRepository;
import com.team4.services.PlayerService;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceUnitTest {
	
	@Mock
	private PlayerRepository playerRepo;
	
	@InjectMocks
	private PlayerService playerService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddValidPlayer() {
		Player mockPlayer = new Player();
		mockPlayer.setUsername("testPlayer");
		mockPlayer.setPassword("testPass");
		
		when(playerRepo.saveAndFlush(any(Player.class))).thenReturn(mockPlayer);
		
		Player player = new Player();
		player.setUsername("testingPlayer");
		player.setPassword("testing");
		
		Player resultPlayer = playerService.createPlayer(player);
		
		
		assertEquals("testPlayer", resultPlayer.getUsername());
		assertEquals("testPass", resultPlayer.getPassword());
	}
	

	@Test
	public void testGetAllPlayers() {
		Player player = new Player();
		player.setUsername("testPlayer");
		player.setEmail("testPlayer@email.com");
		player.setPassword("testPass");
		
		Player player2 = new Player();
		player2.setUsername("testPlayer2");
		player2.setEmail("testPlayer2@email.com");
		player2.setPassword("testPass");
		
		List<Player> mockPlayers = new ArrayList<>();
		mockPlayers.add(player);
		mockPlayers.add(player2);
		
		when(playerRepo.findAll()).thenReturn(mockPlayers);
		
		
		List<Player> resultPlayers = playerService.getAllPlayers();
		
		assertEquals(mockPlayers, resultPlayers);
	}
	
	@Test
	public void testGetPlayerByID() {
		Long playerID = 1L;
		Player savedPlayer = new Player();
		savedPlayer.setUsername("testPlayer");
		savedPlayer.setPassword("testPass");
		
		when(playerRepo.getReferenceById(playerID)).thenReturn(savedPlayer);
		
		Player resultPlayer = playerService.getPlayerById(playerID);
		
		assertEquals("testPlayer", resultPlayer.getUsername());
		assertEquals("testPass", resultPlayer.getPassword());
	}
	
	@Test
	public void testUpdatePlayer() {
		Player updatePlayer = new Player();
		updatePlayer.setUsername("testPlayer");
		updatePlayer.setPassword("testPass");

		when(playerRepo.saveAndFlush(any(Player.class))).thenReturn(updatePlayer);
		
		Player player = new Player();
		player.setUsername("testPlayers");
		player.setPassword("testPassers");
		
		Player resultPlayer = playerService.updatePlayer(player);
		
		assertEquals("testPlayer", resultPlayer.getUsername());
		assertEquals("testPass", resultPlayer.getPassword());
	}
	
	@Test
	public void testDeletePlayerById() throws Exception {
		Long playerID = 1L;
		
		doNothing().when(playerRepo).deleteById(playerID);
		
		playerService.deletePlayerById(playerID);
		
		verify(playerRepo, times(1)).deleteById(anyLong());
	}
	
}
