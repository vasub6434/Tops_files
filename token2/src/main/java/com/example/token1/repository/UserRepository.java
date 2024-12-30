package com.example.token1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.token1.bean.User;

public interface UserRepository extends JpaRepository<User,Long> {

	public Optional<User> findByEmail(String email);
}
