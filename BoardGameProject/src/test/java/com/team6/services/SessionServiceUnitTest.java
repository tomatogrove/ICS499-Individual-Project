package com.team6.services;

import com.team6.model.util.Session;
import com.team6.model.util.UserAccount;
import com.team6.repositories.SessionRepository;
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
public class SessionServiceUnitTest {

    @Mock
    private SessionRepository sessionRepo;

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private SessionService sessionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddValidSession() {
        Session mockSession = new Session();
        UserAccount mockUserAccount = new UserAccount("testUser", "test@email.com", "testPass");
        mockSession.setUserAccount(mockUserAccount);


        when(sessionRepo.saveAndFlush(any(Session.class))).thenReturn(mockSession);
        when(userAccountService.findUserByEmailAndPassword(any(String.class), any(String.class))).thenReturn(mockUserAccount);

        Session session = new Session();
        session.setUserAccount(new UserAccount("testUser2", "test2@email.com", "testPass"));

        Session resultSession = sessionService.createSession(session);

        assertEquals(mockSession, resultSession);
    }


    @Test
    public void testGetAllSessions() {
        Session session = new Session();
        session.setSessionKey(Session.generateSessionKey());

        Session session2 = new Session();
        session2.setSessionKey(Session.generateSessionKey());

        List<Session> mockSessions = new ArrayList<>();
        mockSessions.add(session);
        mockSessions.add(session2);

        when(sessionRepo.findAll()).thenReturn(mockSessions);

        List<Session> resultSessions = sessionService.getAllSessions();

        assertEquals(mockSessions, resultSessions);
    }

    @Test
    public void testGetSessionByID() {
        Long sessionID = 1L;
        Session savedSession = new Session();
        savedSession.setSessionKey(Session.generateSessionKey());

        when(sessionRepo.findById(sessionID)).thenReturn(Optional.of(savedSession));

        Session resultSession = sessionService.getSessionById(sessionID);
        resultSession.setSessionKey(Session.generateSessionKey());

        assertEquals(savedSession.getSessionKey(), resultSession.getSessionKey());
    }

    @Test
    public void testUpdateSession() {
        Session updateSession = new Session();
        updateSession.setSessionKey(Session.generateSessionKey());

        when(sessionRepo.saveAndFlush(any(Session.class))).thenReturn(updateSession);

        Session session = new Session();
        updateSession.setSessionKey(Session.generateSessionKey());

        Session resultSession = sessionService.updateSession(session);

        assertEquals(updateSession.getSessionKey(), resultSession.getSessionKey());
    }

    @Test
    public void testDeleteSessionById() throws Exception {
        Long sessionID = 1L;

        doNothing().when(sessionRepo).deleteById(sessionID);

        sessionService.deleteSessionById(sessionID);

        verify(sessionRepo, times(1)).deleteById(anyLong());
    }

}

