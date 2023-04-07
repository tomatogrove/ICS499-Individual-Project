package com.team4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.Session;

import jakarta.transaction.Transactional;

public interface SessionRepository extends JpaRepository<Session, Long> {
	
    Optional<Session> findBySessionKey(String key);

    @Transactional
	void deleteBySessionKey(String key);

}