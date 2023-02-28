package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
