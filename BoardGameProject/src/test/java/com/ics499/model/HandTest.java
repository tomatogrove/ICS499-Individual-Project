package com.ics499.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.team4.model.Card;
import com.team4.model.Hand;

public class HandTest {

    private Hand hand;

    @Before
    public void setUp() {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("Red", 5));
        cards.add(new Card("Blue", 8));
        hand = new Hand(1L, "My Hand", cards);
    }

    @Test
    public void testGetHandID() {
        assertEquals(Long.valueOf(1), hand.getHandID());
    }

    @Test
    public void testSetHandID() {
        hand.setHandID(2L);
        assertEquals(Long.valueOf(2), hand.getHandID());
    }
    @Test
    public void testGetCards() {
        assertEquals(2, hand.getCards().size());
        assertEquals("Red", hand.getCards().get(0).getColor());
        assertEquals(5, hand.getCards().get(0).getType());
        assertEquals("Blue", hand.getCards().get(1).getColor());
        assertEquals(8, hand.getCards().get(1).getType());
    }

    @Test
    public void testSetCards() {
        List<Card> newCards = new ArrayList<Card>();
        newCards.add(new Card("Green", 3));
        newCards.add(new Card("Yellow", 2));
        hand.setCards(newCards);
        assertEquals(2, hand.getCards().size());
        assertEquals("Green", hand.getCards().get(0).getColor());
        assertEquals(3, hand.getCards().get(0).getType());
        assertEquals("Yellow", hand.getCards().get(1).getColor());
        assertEquals(2, hand.getCards().get(1).getType());
    }

    @Test
    public void testGetName() {
        assertEquals("My Hand", hand.getName());
    }

    @Test
    public void testSetName() {
        hand.setName("New Hand");
        assertEquals("New Hand", hand.getName());
    }
}
