package com.team6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team6.model.Chess;
import com.team6.model.Chess.Status;
import com.team6.services.ChessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ChessControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private ChessService chessService;
	
	@InjectMocks
	private ChessController chessController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.chessController).build();
	}
	
	@Test
	public void testCreateChess() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/chess/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllChess() throws Exception {
		Chess chess = new Chess();
		chess.setStatus(Status.ACTIVE);
		
		Chess chess2 = new Chess();
		chess2.setStatus(Status.DONE);
		
		List<Chess> mockChesss = new ArrayList<>();
		mockChesss.add(chess);
		mockChesss.add(chess2);
		
		when(chessService.getAllChessGames()).thenReturn(mockChesss);
		
		
		mockMvc.perform(get("/chess/all"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(mockChesss)));

	}
	
	@Test
	public void testGetChessByID() throws Exception {
		Long chessID = 1L;
		Chess savedChess = new Chess();
		savedChess.setStatus(Status.ACTIVE);
		
		when(chessService.getChessById(chessID)).thenReturn(savedChess);
		
		mockMvc.perform(get("/chess/{id}", chessID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedChess)));
	}
	
	@Test
	public void testDeleteChessById() throws Exception {
		Long chessID = 1L;
		
		doNothing().when(chessService).deleteChessById(chessID);
		
		mockMvc.perform(delete("/chess/delete/{id}", chessID)).andExpect(status().isOk());
	}
}
