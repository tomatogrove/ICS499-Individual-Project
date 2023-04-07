package com.team4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password);
	
}
