package com.ics499.team4.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.team4.model.Knight;
import com.team4.model.Piece;
import com.team4.repositories.KnightRepository;
import com.team4.services.KnightService;

@RunWith(MockitoJUnitRunner.class)
public class KnightServiceUnitTest {
	
	@Mock
	private KnightRepository knightRepo;
	
	@InjectMocks
	private KnightService knightService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddValidKnight() {
		Knight mockKnight = new Knight();
		mockKnight.setColor(Piece.Color.BLACK);
		
		when(knightRepo.saveAndFlush(any(Knight.class))).thenReturn(mockKnight);
		
		Knight knight = new Knight();
		knight.setColor(Piece.Color.WHITE);
		
		Knight resultKnight = knightService.createKnight(knight);
		
		
		assertEquals(Piece.Color.BLACK, resultKnight.getColor());
	}
	

	@Test
	public void testGetAllKnights() {
		Knight knight = new Knight();
		knight.setColor(Piece.Color.BLACK);
		
		Knight knight2 = new Knight();
		knight2.setColor(Piece.Color.WHITE);
		
		List<Knight> mockKnights = new ArrayList<>();
		mockKnights.add(knight);
		mockKnights.add(knight2);
		
		when(knightRepo.findAll()).thenReturn(mockKnights);
		
		
		List<Knight> resultKnights = knightService.getAllKnights();
		
		assertEquals(mockKnights, resultKnights);
	}
	
	@Test
	public void testGetKnightByID() {
		Long knightID = 1L;
		Knight savedKnight = new Knight();
		savedKnight.setColor(Piece.Color.BLACK);
		
		when(knightRepo.getReferenceById(knightID)).thenReturn(savedKnight);
		
		Knight resultKnight = knightService.getKnightById(knightID);
		
		assertEquals(Piece.Color.BLACK, resultKnight.getColor());
	}
	
	@Test
	public void testUpdateKnight() {
		Knight updateKnight = new Knight();
		updateKnight.setColor(Piece.Color.BLACK);

		when(knightRepo.saveAndFlush(any(Knight.class))).thenReturn(updateKnight);
		
		Knight knight = new Knight();
		knight.setColor(Piece.Color.WHITE);
		
		Knight resultKnight = knightService.updateKnight(knight);
		
		assertEquals(Piece.Color.BLACK, resultKnight.getColor());
	}
	
	@Test
	public void testDeleteKnightById() throws Exception {
		Long knightID = 1L;
		
		doNothing().when(knightRepo).deleteById(knightID);
		
		knightService.deleteKnightById(knightID);
		
		verify(knightRepo, times(1)).deleteById(anyLong());
	}
	
}

