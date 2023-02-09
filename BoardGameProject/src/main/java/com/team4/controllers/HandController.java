package com.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.model.classes.Hand;
import com.team4.service.HandService;

@RestController
@RequestMapping("/api/hand")
public class HandController {
	
	@Autowired
	private HandService handService;
	
	@GetMapping
	public List<Hand> getAllHands() {
		return handService.getAllHands();
	}
	
	@PostMapping
	public Hand createHand(@RequestBody Hand hand) {
		return handService.createHand(hand);
	}
}
