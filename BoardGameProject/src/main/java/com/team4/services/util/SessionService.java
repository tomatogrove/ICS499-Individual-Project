package com.team4.services.util;

import com.team4.model.util.Session;
import com.team4.model.util.UserAccount;
import com.team4.repositories.util.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepo;

	@Autowired
	private UserAccountService userAccountService;

	public SessionService(SessionRepository sessionRepo) {
		this.sessionRepo = sessionRepo;
	}

	public Session createSession(Session session) {
		UserAccount account = session.getUserAccount();
		account = userAccountService.findUserByEmailAndPassword(account.getEmail(), account.getPassword());
		if (account != null) {
			List<Session> allSessions = this.getAllSessions();
			Session sesh;
			String potentialKey;
			do {
				potentialKey = Session.generateSessionKey();
				String key = potentialKey;
				sesh = allSessions.stream().filter((testSession) -> testSession.getSessionKey().equals(key)).findFirst().orElse(null);
			} while(sesh != null);


			session.setSessionKey(potentialKey);
			session.setUserAccount(account);
			session = sessionRepo.saveAndFlush(session);
			account.setSession(session);
			userAccountService.updateUser(account);
			session.setUserAccount(account);
			return session;
		}
		return null;
	}

	public List<Session> getAllSessions() {
		return sessionRepo.findAll();
	}

	public Session getSessionById(Long id) {
		return sessionRepo.findById(id).orElse(null);
	}

	public Session getSessionByKey(String key) {
		Session session = sessionRepo.findBySessionKey(key).orElse(null);
		return session;
	}

	public Session updateSession(Session session) {
		return sessionRepo.saveAndFlush(session);
	}

	public boolean deleteSessionById(Long id) {
		Session session = getSessionById(id);
		if (session != null) {
			UserAccount user = session.getUserAccount();
			user.setSession(null);
			userAccountService.updateUser(user);
		}
		sessionRepo.deleteById(id);
		return getSessionById(id) == null;
	}

	public void deleteSessionByKey(String key) {
		sessionRepo.deleteBySessionKey(key);

	}

}
