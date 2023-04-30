package com.team6.services;

import com.team6.model.pieces.King;
import com.team6.model.pieces.Knight;
import com.team6.model.pieces.Pawn;
import com.team6.model.pieces.Piece;
import com.team6.model.pieces.Rook;
import com.team6.repositories.PieceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PieceServiceUnitTest {

    @Mock
    private PieceRepository pieceRepo;

    @InjectMocks
    private PieceService pieceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddValidPiece() {
        Pawn mockPiece = new Pawn();
        mockPiece.setColor(Piece.Color.BLACK);
        mockPiece.setHasMoved(false);

        when(pieceRepo.saveAndFlush(any(Piece.class))).thenReturn(mockPiece);

        Pawn piece = new Pawn();
        piece.setColor(Piece.Color.WHITE);
        piece.setHasMoved(true);

        Pawn resultPiece = (Pawn) pieceService.createPiece(piece);

        assertEquals(Piece.Color.BLACK, resultPiece.getColor());
        assertFalse(resultPiece.getHasMoved());
    }


    @Test
    public void testGetAllPieces() {
        Piece piece = new Knight();
        piece.setColor(Piece.Color.BLACK);

        Piece piece2 = new Knight();
        piece2.setColor(Piece.Color.WHITE);

        List<Piece> mockPieces = new ArrayList<>();
        mockPieces.add(piece);
        mockPieces.add(piece2);

        when(pieceRepo.findAll()).thenReturn(mockPieces);


        List<Piece> resultPieces = pieceService.getAllPieces();

        assertEquals(mockPieces, resultPieces);
    }

    @Test
    public void testGetPieceByID() {
        Long pieceID = 1L;
        Rook savedPiece = new Rook();
        savedPiece.setColor(Piece.Color.BLACK);
        savedPiece.setHasMoved(false);

        when(pieceRepo.findById(pieceID)).thenReturn(Optional.of(savedPiece));

        Rook resultPiece = (Rook) pieceService.getPieceById(pieceID);

        assertEquals(Piece.Color.BLACK, resultPiece.getColor());
        assertFalse(resultPiece.getHasMoved());
    }

    @Test
    public void testUpdatePiece() {
        King updatePiece = new King();
        updatePiece.setColor(Piece.Color.BLACK);
        updatePiece.setHasMoved(false);

        when(pieceRepo.saveAndFlush(any(Piece.class))).thenReturn(updatePiece);

        King piece = new King();
        piece.setColor(Piece.Color.WHITE);
        piece.setHasMoved(true);

        King resultPiece = (King) pieceService.updatePiece(piece);

        assertEquals(Piece.Color.BLACK, resultPiece.getColor());
        assertFalse(resultPiece.getHasMoved());
    }

    @Test
    public void testDeletePieceById() throws Exception {
        Long pieceID = 1L;

        doNothing().when(pieceRepo).deleteById(pieceID);

        pieceService.deletePieceById(pieceID);

        verify(pieceRepo, times(1)).deleteById(anyLong());
    }

}
