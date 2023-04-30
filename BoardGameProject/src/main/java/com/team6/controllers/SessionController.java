package com.team6.controllers;

import com.team6.model.util.Session;
import com.team6.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
	

 	@Autowired 
	private SessionService sessionService;

	@GetMapping("/echo/{key}")
	public Session getSessionByKey(@PathVariable String key) {
		return sessionService.getSessionByKey(key);
	}
	
	@PostMapping("/login")
	public Session create(@RequestBody final Session session) {
		return sessionService.createSession(session);
	}
	
	@PutMapping("/update")
	public Session update(@RequestBody final Session session) {
		return sessionService.updateSession(session);
	}
	
	@DeleteMapping("/logout/{id}")
	public boolean delete(@PathVariable Long id) {
		return sessionService.deleteSessionById(id);
	}
	
	@DeleteMapping("/delete/{key}")
	public void deleteByKey(@PathVariable String key) {
		sessionService.deleteSessionByKey(key);
	}

}
