package com.team4.repositories.util;

import com.team4.model.util.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	Optional<UserAccount> findUserAccountByEmailAndPassword(String email, String password);

    Optional<UserAccount> findUserAccountBySessionSessionID(Long sessionID);
}
