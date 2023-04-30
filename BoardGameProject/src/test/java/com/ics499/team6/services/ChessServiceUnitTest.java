package com.ics499.team6.services;

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

import com.team6.model.Chess;
import com.team6.model.Chess.Status;
import com.team6.repositories.ChessRepository;
import com.team6.services.ChessService;

@RunWith(MockitoJUnitRunner.class)
public class ChessServiceUnitTest {
	
	@Mock
	private ChessRepository chessRepo;
	
	@InjectMocks
	private ChessService chessService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddValidChess() {
		Chess mockChess = new Chess();
		mockChess.setStatus(Status.ACTIVE);
		
		when(chessRepo.saveAndFlush(any(Chess.class))).thenReturn(mockChess);
		
		Chess chess = new Chess();
		chess.setStatus(Status.WON);
		
		Chess resultChess = chessService.createChess(chess);
		
		
		assertEquals(Status.ACTIVE, resultChess.getStatus());
	}
	

	@Test
	public void testGetAllChesss() {
		Chess chess = new Chess();
		chess.setStatus(Status.ACTIVE);
		
		Chess chess2 = new Chess();
		chess2.setStatus(Status.LOST);
		
		List<Chess> mockChesss = new ArrayList<>();
		mockChesss.add(chess);
		mockChesss.add(chess2);
		
		when(chessRepo.findAll()).thenReturn(mockChesss);
		
		List<Chess> resultChesss = chessService.getAllChessGames();
		
		assertEquals(mockChesss, resultChesss);
	}
	
	@Test
	public void testGetChessByID() {
		Long chessID = 1L;
		Chess savedChess = new Chess();
		savedChess.setStatus(Status.ACTIVE);
		
		when(chessRepo.getReferenceById(chessID)).thenReturn(savedChess);
		
		Chess resultChess = chessService.getChessById(chessID);
		
		assertEquals(Status.ACTIVE, resultChess.getStatus());
	}
	
	@Test
	public void testUpdateChess() {
		Chess updateChess = new Chess();
		updateChess.setStatus(Status.ACTIVE);

		when(chessRepo.saveAndFlush(any(Chess.class))).thenReturn(updateChess);
		
		Chess chess = new Chess();
		chess.setStatus(Status.LOST);
		
		Chess resultChess = chessService.updateChess(chess);
		
		assertEquals(Status.ACTIVE, resultChess.getStatus());
	}
	
	@Test
	public void testDeleteChessById() throws Exception {
		Long chessID = 1L;
		
		doNothing().when(chessRepo).deleteById(chessID);
		
		chessService.deleteChessById(chessID);
		
		verify(chessRepo, times(1)).deleteById(anyLong());
	}
	
}

