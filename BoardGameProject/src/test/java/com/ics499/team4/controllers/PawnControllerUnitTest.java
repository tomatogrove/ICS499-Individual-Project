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
import com.team4.controllers.PawnController;
import com.team4.model.Pawn;
import com.team4.services.PawnService;

@RunWith(MockitoJUnitRunner.class)
public class PawnControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private PawnService pawnService;
	
	@InjectMocks
	private PawnController pawnController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.pawnController).build();
	}
	
	
	@Test
	public void testCreateValidPawn() throws Exception {
		Pawn mockPawn = new Pawn();
		mockPawn.setHasMoved(false);
		
		// whenever a post call is make for a Pawn, return the mock pawn
		when(pawnService.createPawn(any(Pawn.class))).thenReturn(mockPawn);
		
		Pawn pawn = new Pawn();
		pawn.setHasMoved(false);
		
		mockMvc.perform(post("/pawn/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(pawn)))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testCreateInvalidPawn() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/pawn/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllPawns() throws Exception {
		Pawn pawn = new Pawn();
		pawn.setHasMoved(false);
		
		Pawn pawn2 = new Pawn();
		pawn.setHasMoved(true);
		
		List<Pawn> mockPawns = new ArrayList<>();
		mockPawns.add(pawn);
		mockPawns.add(pawn2);
		
		when(pawnService.getAllPawns()).thenReturn(mockPawns);
		
		
		mockMvc.perform(get("/pawn/all"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(mockPawns)));

	}
	
	@Test
	public void testGetPawnByID() throws Exception {
		Long pawnID = 1L;
		Pawn savedPawn = new Pawn();
		savedPawn.setHasMoved(false);
		
		when(pawnService.getPawnById(pawnID)).thenReturn(savedPawn);
		
		mockMvc.perform(get("/pawn/{id}", pawnID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedPawn)));
	}
	
	@Test
	public void testUpdatePawn() throws Exception {
		Pawn updatedPawn = new Pawn();
		updatedPawn.setHasMoved(false);

		when(pawnService.updatePawn(any(Pawn.class))).thenReturn(updatedPawn);
		
		mockMvc.perform(put("/pawn/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedPawn)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(updatedPawn)));
	}
	
	@Test
	public void testDeletePawnById() throws Exception {
		Long pawnID = 1L;
		
		doNothing().when(pawnService).deletePawnById(pawnID);
		
		mockMvc.perform(delete("/pawn/delete/{id}", pawnID)).andExpect(status().isOk());
	}
}
