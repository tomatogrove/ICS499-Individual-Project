package com.ics499.team4.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.team4.controllers.PlayerController;
import com.team4.model.Player;
import com.team4.services.PlayerService;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private PlayerService playerService;
	
	@InjectMocks
	private PlayerController playerController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.playerController).build();
	}
	
	
	@Test
	public void testCreateValidPlayer() throws Exception {
		Player mockPlayer = new Player();
		mockPlayer.setUsername("testPlayer");
		
		// whenever a post call is make for a Player, return the mock player
		when(playerService.createPlayer(any(Player.class))).thenReturn(mockPlayer);
		
		Player player = new Player();
		player.setUsername("testPlayer");
		player.setEmail("testPlayer@email.com");
		player.setPassword("testPass");
		
		mockMvc.perform(post("/players/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(player)))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testCreateInvalidPlayer() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/players/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllPlayers() throws Exception {
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
		
		when(playerService.getAllPlayers()).thenReturn(mockPlayers);
		
		
		mockMvc.perform(get("/players/all"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(mockPlayers)));

	}
	
	@Test
	public void testGetPlayerByID() throws Exception {
		Long playerID = 1L;
		Player savedPlayer = new Player();
		savedPlayer.setUsername("testPlayer");
		savedPlayer.setPassword("testPass");
		
		when(playerService.getPlayerById(playerID)).thenReturn(savedPlayer);
		
		mockMvc.perform(get("/players/{id}", playerID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedPlayer)));
	}
	
	@Test
	public void testUpdatePlayer() throws Exception {
		Player updatedPlayer = new Player();
		updatedPlayer.setUsername("testPlayer");
		updatedPlayer.setPassword("safeTest");

		when(playerService.updatePlayer(any(Player.class))).thenReturn(updatedPlayer);
		
		mockMvc.perform(put("/players/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedPlayer)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(updatedPlayer)));
	}
	
	@Test
	public void testDeletePlayerById() throws Exception {
		Long playerID = 1L;
		
		doNothing().when(playerService).deletePlayerById(playerID);
		
		mockMvc.perform(delete("/players/delete/{id}", playerID)).andExpect(status().isOk());
	}
	
}
