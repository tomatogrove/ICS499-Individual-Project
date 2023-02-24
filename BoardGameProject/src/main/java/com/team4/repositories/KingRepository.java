package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.classes.chess.pieces.King;

public interface KingRepository extends JpaRepository<King, Long> {

}

