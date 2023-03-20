package com.team4.controllers;

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
import com.team4.controllers.KingController;
import com.team4.model.King;
import com.team4.services.KingService;

@RunWith(MockitoJUnitRunner.class)
class KingControllerTest {

	private MockMvc mockMvc;

 	private ObjectMapper objectMapper = new ObjectMapper();
 	private ObjectWriter objectWriter = objectMapper.writer();
 	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

 	@Mock
 	private KingService kingService;
 	

 	@InjectMocks
 	private KingController kingController;

 	@BeforeEach
 	public void setUp() {
 		MockitoAnnotations.openMocks(this);
 		this.mockMvc = MockMvcBuilders.standaloneSetup(this.kingController).build();
 	}


 	@Test
 	public void testCreateValidKnight() throws Exception {
 		King testKing = new King();

 		// whenever a post call is make for a King, return the mock king
 		when(kingService.createKing(any(King.class))).thenReturn(testKing);

 		King king = new King();
 		king.setHasMoved(false);

 		mockMvc.perform(post("/king/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(king)))
 			.andExpect(status().isOk())
 			.andReturn();
 	}
 	
 	@Test
 	public void testCreateInvalidKing() throws Exception {
 		// test makes a post call with no body
 		mockMvc.perform(post("/king/add")).andExpect(status().isBadRequest());
 	}
 	
 	@Test
 	public void testGetAllKing() throws Exception {
 		King king = new King();
 		king.setHasMoved(false);

 		King king2 = new King();
 		king.setHasMoved(true);

 		List<King> testKings = new ArrayList<>();
 		testKings.add(king);
 		testKings.add(king2);

 		when(kingService.getAllKing()).thenReturn(testKings);


 		mockMvc.perform(get("/king/all"))
 			.andExpect(status().isOk())
 			.andExpect(content().json(objectWriter.writeValueAsString(testKings)));

 	}

 	@Test
 	public void testGetKingByID() throws Exception {
 		Long kingID = 1L;
 		King savedKing = new King();
 		savedKing.setHasMoved(false);

 		when(kingService.getKingById(kingID)).thenReturn(savedKing);

 		mockMvc.perform(get("/king/{id}", kingID))
 		.andExpect(status().isOk())
 		.andExpect(content().json(objectWriter.writeValueAsString(savedKing)));
 	}

 	@Test
 	public void testUpdateKing() throws Exception {
 		King updatedKing = new King();
 		updatedKing.setHasMoved(false);

 		when(kingService.updateKing(any(King.class))).thenReturn(updatedKing);

 		mockMvc.perform(put("/king/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedKing)))
 			.andExpect(status().isOk())
 			.andExpect(content().json(objectWriter.writeValueAsString(updatedKing)));
 	}

 	@Test
 	public void testDeleteKingById() throws Exception {
 		Long kingID = 1L;

 		doNothing().when(kingService).deleteKingById(kingID);

 		mockMvc.perform(delete("/king/delete/{id}", kingID)).andExpect(status().isOk());
 	}


}
