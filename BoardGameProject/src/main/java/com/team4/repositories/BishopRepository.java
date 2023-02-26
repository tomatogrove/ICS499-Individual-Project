package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.team4.model.classes.chess.pieces.Bishop;

@Service
public interface BishopRepository extends JpaRepository<Bishop, Long> {

}
