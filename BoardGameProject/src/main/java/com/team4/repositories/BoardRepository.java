package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
