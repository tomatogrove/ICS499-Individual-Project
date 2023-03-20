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
import com.team4.controllers.CardController;
import com.team4.model.Card;
import com.team4.services.CardService;

@RunWith(MockitoJUnitRunner.class)
class CardControllerTest {

	private MockMvc mockMvc;

 	private ObjectMapper objectMapper = new ObjectMapper();
 	private ObjectWriter objectWriter = objectMapper.writer();
 	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

 	@Mock
 	private CardService cardService;
 	

 	@InjectMocks
 	private CardController cardController;

 	@BeforeEach
 	public void setUp() {
 		MockitoAnnotations.openMocks(this);
 		this.mockMvc = MockMvcBuilders.standaloneSetup(this.cardController).build();
 	}


 	@Test
 	public void testCreateValidCard() throws Exception {
 		Card testCard = new Card();

 		// whenever a post call is make for a Card, return the mock card
 		when(cardService.createCard(any(Card.class))).thenReturn(testCard);

 		Card card = new Card();

 		mockMvc.perform(post("/card/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(card)))
 			.andExpect(status().isOk())
 			.andReturn();
 	}
 	
 	@Test
 	public void testCreateInvalidCard() throws Exception {
 		// test makes a post call with no body
 		mockMvc.perform(post("/card/add")).andExpect(status().isBadRequest());
 	}
 	
 	@Test
 	public void testGetAllKing() throws Exception {
 		Card card = new Card();

 		Card card2 = new Card();

 		List<Card> testCards = new ArrayList<>();
 		testCards.add(card);
 		testCards.add(card2);

 		when(cardService.getAllCard()).thenReturn(testCards);


 		mockMvc.perform(get("/card/all"))
 			.andExpect(status().isOk())
 			.andExpect(content().json(objectWriter.writeValueAsString(testCards)));

 	}

 	@Test
 	public void testGetCardByID() throws Exception {
 		Long cardID = 1L;
 		Card savedCard = new Card();

 		when(cardService.getCardById(cardID)).thenReturn(savedCard);

 		mockMvc.perform(get("/card/{id}", cardID))
 		.andExpect(status().isOk())
 		.andExpect(content().json(objectWriter.writeValueAsString(savedCard)));
 	}

 	@Test
 	public void testUpdateCard() throws Exception {
 		Card updatedCard = new Card();

 		when(cardService.updateCard(any(Card.class))).thenReturn(updatedCard);

 		mockMvc.perform(put("/card/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedCard)))
 			.andExpect(status().isOk())
 			.andExpect(content().json(objectWriter.writeValueAsString(updatedCard)));
 	}

 	@Test
 	public void testDeleteCardById() throws Exception {
 		Long cardID = 1L;

 		doNothing().when(cardService).deleteCardById(cardID);

 		mockMvc.perform(delete("/card/delete/{id}", cardID)).andExpect(status().isOk());
 	}


}
