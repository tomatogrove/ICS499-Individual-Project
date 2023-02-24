package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.classes.chess.pieces.Bishop;

public interface BishopRepository extends JpaRepository<Bishop, Long> {

}
