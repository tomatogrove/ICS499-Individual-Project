package com.team4.model;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;


//import java.util.ArrayList;
//import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import com.team4.model.Card;
//import com.team4.model.Hand;

public class CardTest {

	Card card;
	
	@BeforeEach
    public void setUp() {
		card = new Card();
		card.setColor("Red");
        card.setType(8);
    }
	
	@Test
	public void testGetCardID() {
		assertEquals(Long.valueOf(1), card.getCardID());
	}
	
	
	@Test
	public void testSetCardID() {
		card.setCardID(2L);
		assertEquals(Long.valueOf(2), card.getCardID());
	}
	
	@Test
	public void testGetColor() {
		assertEquals("Red", card.getColor());
	}
	
	@Test
	public void testSetColor( ) {
		card.setColor("Red");
        assertEquals("Red", card.getColor());
	}
	
	@Test
	public void testGetType() {
		assertEquals(3, card.getColor());
	}
	
	@Test
	public void testSetType( ) {
		card.setType(3);
        assertEquals(3, card.getColor());
	}
	
//	@Test
//	public void testGetCard() {
//		assertEquals("Blue", card.getColor());
//		assertEquals(3, card.getType());
//	}

}
