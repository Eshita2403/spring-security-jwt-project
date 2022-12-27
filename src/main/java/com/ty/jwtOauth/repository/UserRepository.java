package com.ty.jwtOauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.jwtOauth.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT s from User s where s.username = ?1") 
	public User findUserbyUsername(String username);

}
