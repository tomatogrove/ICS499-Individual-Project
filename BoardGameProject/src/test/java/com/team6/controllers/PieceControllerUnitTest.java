package com.team6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team6.model.pieces.King;
import com.team6.model.pieces.Pawn;
import com.team6.model.pieces.Piece;
import com.team6.model.pieces.Rook;
import com.team6.services.PieceService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PieceControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private PieceService pieceService;
	
	@InjectMocks
	private PieceController pieceController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.pieceController).build();
	}
	
	@Test
	public void testCreateInvalidPiece() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/piece/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllPieces() throws Exception {
		Pawn piece = new Pawn();
		piece.setHasMoved(false);
		
		Rook piece2 = new Rook();
		piece.setHasMoved(true);
		
		List<Piece> mockPieces = new ArrayList<>();
		mockPieces.add(piece);
		mockPieces.add(piece2);
		
		when(pieceService.getAllPieces()).thenReturn(mockPieces);
		
		
		mockMvc.perform(get("/piece/all"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(mockPieces)));

	}
	
	@Test
	public void testGetPieceByID() throws Exception {
		Long pieceID = 1L;
		King savedPiece = new King();
		savedPiece.setHasMoved(false);
		
		when(pieceService.getPieceById(pieceID)).thenReturn(savedPiece);
		
		mockMvc.perform(get("/piece/{id}", pieceID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedPiece)));
	}
	
	@Test
	public void testDeletePieceById() throws Exception {
		Long pieceID = 1L;
		
		doNothing().when(pieceService).deletePieceById(pieceID);
		
		mockMvc.perform(delete("/piece/delete/{id}", pieceID)).andExpect(status().isOk());
	}
}
