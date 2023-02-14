package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.classes.Chess;

public interface ChessRepository extends JpaRepository<Chess, Long> {

}
