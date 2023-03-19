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

import com.team4.model.Chess;
import com.team4.model.Game;
import com.team4.model.Game.Status;
import com.team4.repositories.GameRepository;
import com.team4.services.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceUnitTest {
	
	@Mock
	private GameRepository gameRepo;
	
	@InjectMocks
	private GameService gameService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddValidGame() {
		Game mockGame = new Chess();
		mockGame.setStatus(Status.ACTIVE);
		
		when(gameRepo.saveAndFlush(any(Game.class))).thenReturn(mockGame);
		
		Game game = new Chess();
		game.setStatus(Status.WON);
		
		Game resultGame = gameService.createGame(game);
		
		
		assertEquals(Status.ACTIVE, resultGame.getStatus());
	}
	

	@Test
	public void testGetAllGames() {
		Game game = new Chess();
		game.setStatus(Status.ACTIVE);
		
		Game game2 = new Chess();
		game2.setStatus(Status.LOST);
		
		List<Game> mockGames = new ArrayList<>();
		mockGames.add(game);
		mockGames.add(game2);
		
		when(gameRepo.findAll()).thenReturn(mockGames);
		
		List<Game> resultGames = gameService.getAllGames();
		
		assertEquals(mockGames, resultGames);
	}
	
	@Test
	public void testGetGameByID() {
		Long gameID = 1L;
		Game savedGame = new Chess();
		savedGame.setStatus(Status.ACTIVE);
		
		when(gameRepo.getReferenceById(gameID)).thenReturn(savedGame);
		
		Game resultGame = gameService.getGameById(gameID);
		
		assertEquals(Status.ACTIVE, resultGame.getStatus());
	}
	
	@Test
	public void testUpdateGame() {
		Game updateGame = new Chess();
		updateGame.setStatus(Status.ACTIVE);

		when(gameRepo.saveAndFlush(any(Game.class))).thenReturn(updateGame);
		
		Game game = new Chess();
		game.setStatus(Status.LOST);
		
		Game resultGame = gameService.updateGame(game);
		
		assertEquals(Status.ACTIVE, resultGame.getStatus());
	}
	
	@Test
	public void testDeleteGameById() throws Exception {
		Long gameID = 1L;
		
		doNothing().when(gameRepo).deleteById(gameID);
		
		gameService.deleteGameById(gameID);
		
		verify(gameRepo, times(1)).deleteById(anyLong());
	}
	
}

