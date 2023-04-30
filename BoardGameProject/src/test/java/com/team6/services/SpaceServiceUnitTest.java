package com.team6.services;

import com.team6.model.Space;
import com.team6.model.pieces.Bishop;
import com.team6.model.pieces.King;
import com.team6.model.pieces.Pawn;
import com.team6.model.pieces.Queen;
import com.team6.model.pieces.Rook;
import com.team6.repositories.SpaceRepository;
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
public class SpaceServiceUnitTest {

    @Mock
    private SpaceRepository spaceRepo;

    @InjectMocks
    private SpaceService spaceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddValidSpace() {
        Space mockSpace = new Space();

        when(spaceRepo.saveAndFlush(any(Space.class))).thenReturn(mockSpace);

        Space space = new Space();

        Space resultSpace = spaceService.createSpace(space);


        assertEquals(mockSpace, resultSpace);
    }


    @Test
    public void testGetAllSpaces() {
        Space space = new Space();
        space.setPiece(new Pawn());

        Space space2 = new Space();
        space2.setPiece(new Queen());

        List<Space> mockSpaces = new ArrayList<>();
        mockSpaces.add(space);
        mockSpaces.add(space2);

        when(spaceRepo.findAll()).thenReturn(mockSpaces);

        List<Space> resultSpaces = spaceService.getAllSpaces();

        assertEquals(mockSpaces, resultSpaces);
    }

    @Test
    public void testGetSpaceByID() {
        Long spaceID = 1L;
        Space savedSpace = new Space();
        savedSpace.setPiece(new Bishop());

        when(spaceRepo.findById(spaceID)).thenReturn(Optional.of(savedSpace));

        Space resultSpace = spaceService.getSpaceById(spaceID);

        assertEquals(savedSpace, resultSpace);
    }

    @Test
    public void testUpdateSpace() {
        Space updateSpace = new Space();
        updateSpace.setPiece(new King());

        when(spaceRepo.saveAndFlush(any(Space.class))).thenReturn(updateSpace);

        Space space = new Space();
        space.setPiece(new Rook());

        Space resultSpace = spaceService.updateSpace(space);

        assertEquals(updateSpace, resultSpace);
    }

    @Test
    public void testDeleteSpaceById() throws Exception {
        Long spaceID = 1L;

        doNothing().when(spaceRepo).deleteById(spaceID);

        spaceService.deleteSpaceById(spaceID);

        verify(spaceRepo, times(1)).deleteById(anyLong());
    }

}

