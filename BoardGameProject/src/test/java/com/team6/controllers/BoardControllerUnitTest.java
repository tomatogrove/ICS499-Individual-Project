package com.team6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team6.model.Board;
import com.team6.services.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class BoardControllerUnitTest {


    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.boardController).build();
    }


    @Test
    public void testCreateValidBoard() throws Exception {
        Board mockBoard = new Board();
        mockBoard.setPieces(new ArrayList<>());
        mockBoard.setSpaces(new ArrayList<>());

        // whenever a post call is make for a Board, return the mock board
        when(boardService.createBoard(any(Board.class))).thenReturn(mockBoard);

        Board board = new Board();
        board.setPieces(new ArrayList<>());
        board.setSpaces(new ArrayList<>());

        mockMvc.perform(post("/board/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(board)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testCreateInvalidBoard() throws Exception {
        // test makes a post call with no body
        mockMvc.perform(post("/board/add")).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetBoardByID() throws Exception {
        Long boardID = 1L;
        Board savedBoard = new Board();

        when(boardService.getBoardById(boardID)).thenReturn(savedBoard);

        mockMvc.perform(get("/board/{id}", boardID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(savedBoard)));
    }

    @Test
    public void testUpdateBoard() throws Exception {
        Board updatedBoard = new Board();
        updatedBoard.setPieces(new ArrayList<>());
        updatedBoard.setSpaces(new ArrayList<>());

        when(boardService.updateBoard(any(Board.class))).thenReturn(updatedBoard);

        mockMvc.perform(put("/board/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedBoard)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(updatedBoard)));
    }

    @Test
    public void testDeleteBoardById() throws Exception {
        Long boardID = 1L;

        doNothing().when(boardService).deleteBoardById(boardID);

        mockMvc.perform(delete("/board/delete/{id}", boardID)).andExpect(status().isOk());
    }

}
