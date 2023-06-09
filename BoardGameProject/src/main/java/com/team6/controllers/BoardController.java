package com.team6.controllers;

import com.team6.model.Board;
import com.team6.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

 	@Autowired
	private BoardService boardService;

	@GetMapping("/all")
	public List<Board> list() {
		return boardService.getAllBoards();
	}

	@GetMapping("/{id}")
	public Board get(@PathVariable String id) {
		return boardService.getBoardById(Long.parseLong(id));
	}

	@PostMapping("/add")
	public Board create(@RequestBody final Board board) {
		return boardService.createBoard(board);
	}

	@PutMapping("/update")
	public Board update(@RequestBody final Board board) {
		return boardService.updateBoard(board);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		boardService.deleteBoardById(Long.parseLong(id));
	}

}
