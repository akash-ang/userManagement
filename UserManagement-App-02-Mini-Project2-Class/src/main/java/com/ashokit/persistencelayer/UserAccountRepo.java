package com.ashokit.persistencelayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entities.UserAccountEntity;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccountEntity, Integer> {

	public UserAccountEntity findByUserEmail(String email);

	public UserAccountEntity findByUserEmailAndUserPassword(String email, String password);

}
