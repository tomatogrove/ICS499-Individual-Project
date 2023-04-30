package com.team6.services;

import com.team6.model.Board;
import com.team6.model.Chess;
import com.team6.model.enums.Status;
import com.team6.repositories.BoardRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceUnitTest {

    @Mock
    private BoardRepository boardRepo;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddValidBoard() {
        Board mockBoard = new Board();

        when(boardRepo.saveAndFlush(any(Board.class))).thenReturn(mockBoard);

        Board board = new Board();

        Board resultBoard = boardService.createBoard(board);


        assertEquals(mockBoard, resultBoard);
    }


    @Test
    public void testGetAllBoards() {
        Board board = new Board();
        board.setChess(new Chess());
        board.getChess().setStatus(Status.ACTIVE);

        Board board2 = new Board();
        board2.setChess(new Chess());
        board2.getChess().setStatus(Status.DONE);

        List<Board> mockBoards = new ArrayList<>();
        mockBoards.add(board);
        mockBoards.add(board2);

        when(boardRepo.findAll()).thenReturn(mockBoards);

        List<Board> resultBoards = boardService.getAllBoards();

        assertEquals(mockBoards, resultBoards);
    }

    @Test
    public void testGetBoardByID() {
        Long boardID = 1L;
        Board savedBoard = new Board();
        savedBoard.setChess(new Chess());
        savedBoard.getChess().setStatus(Status.ACTIVE);

        when(boardRepo.findById(boardID)).thenReturn(Optional.of(savedBoard));

        Board resultBoard = boardService.getBoardById(boardID);

        assertEquals(Status.ACTIVE, resultBoard.getChess().getStatus());
    }

    @Test
    public void testUpdateBoard() {
        Board updateBoard = new Board();
        updateBoard.setChess(new Chess());
        updateBoard.getChess().setStatus(Status.ACTIVE);

        when(boardRepo.saveAndFlush(any(Board.class))).thenReturn(updateBoard);

        Board board = new Board();
        board.setChess(new Chess());
        board.getChess().setStatus(Status.DONE);

        Board resultBoard = boardService.updateBoard(board);

        assertEquals(Status.ACTIVE, resultBoard.getChess().getStatus());
    }

    @Test
    public void testDeleteBoardById() throws Exception {
        Long boardID = 1L;

        doNothing().when(boardRepo).deleteById(boardID);

        boardService.deleteBoardById(boardID);

        verify(boardRepo, times(1)).deleteById(anyLong());
    }

}

