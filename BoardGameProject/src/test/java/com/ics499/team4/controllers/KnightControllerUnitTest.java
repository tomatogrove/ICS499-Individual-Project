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
import com.team4.controllers.KnightController;
import com.team4.model.Knight;
import com.team4.services.KnightService;

@RunWith(MockitoJUnitRunner.class)
public class KnightControllerUnitTest {


	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ObjectWriter objectWriter = objectMapper.writer();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Mock
	private KnightService knightService;
	
	@InjectMocks
	private KnightController knightController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.knightController).build();
	}
	
	
	@Test
	public void testCreateValidKnight() throws Exception {
		Knight mockKnight = new Knight();
		
		// whenever a post call is make for a Knight, return the mock knight
		when(knightService.createKnight(any(Knight.class))).thenReturn(mockKnight);
		
		Knight knight = new Knight();
		
		mockMvc.perform(post("/knight/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(knight)))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testCreateInvalidKnight() throws Exception {
		// test makes a post call with no body
		mockMvc.perform(post("/knight/add")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllKnights() throws Exception {
		Knight knight = new Knight();
		
		Knight knight2 = new Knight();
		
		List<Knight> mockKnights = new ArrayList<>();
		mockKnights.add(knight);
		mockKnights.add(knight2);
		
		when(knightService.getAllKnights()).thenReturn(mockKnights);
		
		
		mockMvc.perform(get("/knight/all"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(mockKnights)));

	}
	
	@Test
	public void testGetKnightByID() throws Exception {
		Long knightID = 1L;
		Knight savedKnight = new Knight();
		
		when(knightService.getKnightById(knightID)).thenReturn(savedKnight);
		
		mockMvc.perform(get("/knight/{id}", knightID))
		.andExpect(status().isOk())
		.andExpect(content().json(objectWriter.writeValueAsString(savedKnight)));
	}
	
	@Test
	public void testUpdateKnight() throws Exception {
		Knight updatedKnight = new Knight();

		when(knightService.updateKnight(any(Knight.class))).thenReturn(updatedKnight);
		
		mockMvc.perform(put("/knight/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedKnight)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectWriter.writeValueAsString(updatedKnight)));
	}
	
	@Test
	public void testDeleteKnightById() throws Exception {
		Long knightID = 1L;
		
		doNothing().when(knightService).deleteKnightById(knightID);
		
		mockMvc.perform(delete("/knight/delete/{id}", knightID)).andExpect(status().isOk());
	}
}
