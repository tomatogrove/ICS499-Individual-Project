package com.ics499.team4.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team4.controllers.GameController;
import com.team4.model.Chess;
import com.team4.model.Game;
import com.team4.model.Game.Status;
import com.team4.services.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private GameService gameService;
	
	@InjectMocks
	private GameController gameController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.gameController).build();
	}
	
	@Test
	public void testCreateGame() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/game/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllGames() throws Exception {
		Game game = new Chess();
		game.setStatus(Status.ACTIVE);
		
		Game game2 = new Chess();
		game2.setStatus(Status.WON);
		
		List<Game> mockGames = new ArrayList<>();
		mockGames.add(game);
		mockGames.add(game2);
		
		when(gameService.getAllGames()).thenReturn(mockGames);
		
		
		mockMvc.perform(get("/game/all"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(mockGames)));

	}
	
	@Test
	public void testGetGameByID() throws Exception {
		Long gameID = 1L;
		Game savedGame = new Chess();
		savedGame.setStatus(Status.ACTIVE);
		
		when(gameService.getGameById(gameID)).thenReturn(savedGame);
		
		mockMvc.perform(get("/game/{id}", gameID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedGame)));
	}
	
	@Test
	public void testDeleteGameById() throws Exception {
		Long gameID = 1L;
		
		doNothing().when(gameService).deleteGameById(gameID);
		
		mockMvc.perform(delete("/game/delete/{id}", gameID)).andExpect(status().isOk());
	}
}
