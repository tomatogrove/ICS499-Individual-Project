package com.team4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.model.Space;
import com.team4.services.SpaceService;

@RestController
@RequestMapping("/space")
public class SpaceController {

	@Autowired 
	private SpaceService spaceService;
	
	@GetMapping("/all")
	public List<Space> list() {
		return spaceService.getAllSpaces();
	}
	
	@GetMapping("/{id}")
	public Space get(@PathVariable Long id) {
		return spaceService.getSpaceById(id);
	}
	
	@PostMapping("/add")
	public Space create(@RequestBody final Space space) {
		return spaceService.createSpace(space);
	}
	
	@PutMapping("/update")
	public Space update(@RequestBody final Space space) {
		return spaceService.updateSpace(space);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		spaceService.deleteSpaceById(id);
	}

}
