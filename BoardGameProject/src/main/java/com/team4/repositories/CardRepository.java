package com.team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}