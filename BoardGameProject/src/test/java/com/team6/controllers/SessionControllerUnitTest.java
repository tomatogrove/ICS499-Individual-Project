package com.team6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.team6.model.util.Session;
import com.team6.model.util.UserAccount;
import com.team6.services.SessionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class SessionControllerUnitTest {


    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionController sessionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.sessionController).build();
    }


    @Test
    public void testCreateValidSession() throws Exception {
        Session mockSession = new Session();

        // whenever a post call is make for a Session, return the mock session 
        when(sessionService.createSession(any(Session.class))).thenReturn(mockSession);

        Session session = new Session();

        mockMvc.perform(post("/session/login").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(session)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testCreateInvalidSession() throws Exception {
        // test makes a post call with no body
        mockMvc.perform(post("/session/login")).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateSession() throws Exception {
        Session updatedSession = new Session();
        updatedSession.setUserAccount(new UserAccount());

        when(sessionService.updateSession(any(Session.class))).thenReturn(updatedSession);

        mockMvc.perform(put("/session/update").contentType(APPLICATION_JSON_UTF8).content(objectWriter.writeValueAsString(updatedSession)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(updatedSession)));
    }

    @Test
    public void testDeleteSessionByKey() throws Exception {
        String sessionKey = Session.generateSessionKey();

        doNothing().when(sessionService).deleteSessionByKey(sessionKey);

        mockMvc.perform(delete("/session/delete/{key}", sessionKey)).andExpect(status().isOk());
    }

}
