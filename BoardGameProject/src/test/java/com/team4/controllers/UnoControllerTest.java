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
import com.team4.controllers.UnoController;
import com.team4.model.Uno;
import com.team4.services.UnoService;

@RunWith(MockitoJUnitRunner.class)
class UnoControllerTest {

	private MockMvc mockMvc;

 	private ObjectMapper objectMapper = new ObjectMapper();
 	private ObjectWriter objectWriter = objectMapper.writer();
 	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

 	@Mock
 	private UnoService unoService;
 	

 	@InjectMocks
 	private UnoController unoController;

 	@BeforeEach
 	public void setUp() {
 		MockitoAnnotations.openMocks(this);
 		this.mockMvc = MockMvcBuilders.standaloneSetup(this.unoController).build();
 	}


 	@Test
 	public void testCreateValidCard() throws Exception {
 		Uno testUno = new Uno();

 		// whenever a post call is make for a Card, return the mock card
 		when(unoService.createUno(any(Uno.class))).thenReturn(testUno);

 		Uno uno = new Uno();

 		mockMvc.perform(post("/uno/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(uno)))
 			.andExpect(status().isOk())
 			.andReturn();
 	}
 	
 	@Test
 	public void testCreateInvalidCard() throws Exception {
 		// test makes a post call with no body
 		mockMvc.perform(post("/uno/add")).andExpect(status().isBadRequest());
 	}
 	
 	@Test
 	public void testGetAllUno() throws Exception {
 		Uno uno = new Uno();

 		Uno uno2 = new Uno();

 		List<Uno> testUnos = new ArrayList<>();
 		testUnos.add(uno);
 		testUnos.add(uno2);

 		when(unoService.getAllUno()).thenReturn(testUnos);


 		mockMvc.perform(get("/uno/all"))
 			.andExpect(status().isOk())
 			.andExpect(content().json(objectWriter.writeValueAsString(testUnos)));

 	}

 	@Test
 	public void testGetUnoByID() throws Exception {
 		Long unoID = 1L;
 		Uno savedUno = new Uno();

 		when(unoService.getUnoById(unoID)).thenReturn(savedUno);

 		mockMvc.perform(get("/uno/{id}", unoID))
 		.andExpect(status().isOk())
 		.andExpect(content().json(objectWriter.writeValueAsString(savedUno)));
 	}

 	@Test
 	public void testUpdateUno() throws Exception {
 		Uno updatedUno = new Uno();

 		when(unoService.updateUno(any(Uno.class))).thenReturn(updatedUno);

 		mockMvc.perform(put("/uno/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedUno)))
 			.andExpect(status().isOk())
 			.andExpect(content().json(objectWriter.writeValueAsString(updatedUno)));
 	}

 	@Test
 	public void testDeleteUnoById() throws Exception {
 		Long unoID = 1L;

 		doNothing().when(unoService).deleteUnoById(unoID);

 		mockMvc.perform(delete("/uno/delete/{id}", unoID)).andExpect(status().isOk());
 	}


}
