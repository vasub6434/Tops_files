package com.example.token1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.token1.bean.User;
import com.example.token1.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public String createusUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		return userRepository.save(user);
		this.userRepository.save(user);
		return "user is sucessfully created...";
	}
}
