package com.team6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team6.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
