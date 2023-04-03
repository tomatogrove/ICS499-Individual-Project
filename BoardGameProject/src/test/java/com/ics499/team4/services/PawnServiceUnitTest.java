package com.ics499.team4.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import com.team4.model.Pawn;
import com.team4.model.Piece;
import com.team4.repositories.PawnRepository;
import com.team4.services.PawnService;

@RunWith(MockitoJUnitRunner.class)
public class PawnServiceUnitTest {
	
	@Mock
	private PawnRepository pawnRepo;
	
	@InjectMocks
	private PawnService pawnService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddValidPawn() {
		Pawn mockPawn = new Pawn();
		mockPawn.setColor(Piece.Color.BLACK);
		mockPawn.setHasMoved(false);
		
		when(pawnRepo.saveAndFlush(any(Pawn.class))).thenReturn(mockPawn);
		
		Pawn pawn = new Pawn();
		pawn.setColor(Piece.Color.WHITE);
		pawn.setHasMoved(true);
		
		Pawn resultPawn = pawnService.createPawn(pawn);
		
		assertEquals(Piece.Color.BLACK, resultPawn.getColor());
		assertTrue(!resultPawn.getHasMoved());
	}
	

	@Test
	public void testGetAllPawns() {
		Pawn pawn = new Pawn();
		pawn.setColor(Piece.Color.BLACK);
		pawn.setHasMoved(false);
		
		Pawn pawn2 = new Pawn();
		pawn2.setColor(Piece.Color.WHITE);
		pawn2.setHasMoved(true);
		
		List<Pawn> mockPawns = new ArrayList<>();
		mockPawns.add(pawn);
		mockPawns.add(pawn2);
		
		when(pawnRepo.findAll()).thenReturn(mockPawns);
		
		
		List<Pawn> resultPawns = pawnService.getAllPawns();
		
		assertEquals(mockPawns, resultPawns);
	}
	
	@Test
	public void testGetPawnByID() {
		Long pawnID = 1L;
		Pawn savedPawn = new Pawn();
		savedPawn.setColor(Piece.Color.BLACK);
		savedPawn.setHasMoved(false);
		
		when(pawnRepo.getReferenceById(pawnID)).thenReturn(savedPawn);
		
		Pawn resultPawn = pawnService.getPawnById(pawnID);
		
		assertEquals(Piece.Color.BLACK, resultPawn.getColor());
		assertTrue(!resultPawn.getHasMoved());
	}
	
	@Test
	public void testUpdatePawn() {
		Pawn updatePawn = new Pawn();
		updatePawn.setColor(Piece.Color.BLACK);
		updatePawn.setHasMoved(false);

		when(pawnRepo.saveAndFlush(any(Pawn.class))).thenReturn(updatePawn);
		
		Pawn pawn = new Pawn();
		pawn.setColor(Piece.Color.WHITE);
		pawn.setHasMoved(true);
		
		Pawn resultPawn = pawnService.updatePawn(pawn);
		
		assertEquals(Piece.Color.BLACK, resultPawn.getColor());
		assertTrue(!resultPawn.getHasMoved());
	}
	
	@Test
	public void testDeletePawnById() throws Exception {
		Long pawnID = 1L;
		
		doNothing().when(pawnRepo).deleteById(pawnID);
		
		pawnService.deletePawnById(pawnID);
		
		verify(pawnRepo, times(1)).deleteById(anyLong());
	}
	
}
