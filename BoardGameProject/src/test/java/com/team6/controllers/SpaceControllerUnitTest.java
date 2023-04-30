package com.team6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team6.model.Space;
import com.team6.model.pieces.Queen;
import com.team6.services.SpaceService;
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
public class SpaceControllerUnitTest {


    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Mock
    private SpaceService spaceService;

    @InjectMocks
    private SpaceController spaceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.spaceController).build();
    }


    @Test
    public void testCreateValidSpace() throws Exception {
        Space mockSpace = new Space();
        mockSpace.setPiece(new Queen());

        // whenever a post call is make for a Space, return the mock space 
        when(spaceService.createSpace(any(Space.class))).thenReturn(mockSpace);

        Space space = new Space();

        mockMvc.perform(post("/space/add").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(space)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testCreateInvalidSpace() throws Exception {
        // test makes a post call with no body
        mockMvc.perform(post("/space/add")).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetSpaceByID() throws Exception {
        Long spaceID = 1L;
        Space savedSpace = new Space();

        when(spaceService.getSpaceById(spaceID)).thenReturn(savedSpace);

        mockMvc.perform(get("/space/{id}", spaceID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(savedSpace)));
    }

    @Test
    public void testUpdateSpace() throws Exception {
        Space updatedSpace = new Space();

        when(spaceService.updateSpace(any(Space.class))).thenReturn(updatedSpace);

        mockMvc.perform(put("/space/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedSpace)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(updatedSpace)));
    }

    @Test
    public void testDeleteSpaceById() throws Exception {
        Long spaceID = 1L;

        doNothing().when(spaceService).deleteSpaceById(spaceID);

        mockMvc.perform(delete("/space/delete/{id}", spaceID)).andExpect(status().isOk());
    }

}
