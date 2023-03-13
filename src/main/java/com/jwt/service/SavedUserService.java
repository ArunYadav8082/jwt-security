package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.dao.UserRepository;
import com.jwt.model.User;

@Service
public class SavedUserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;

	public User saveUser(User user) {
		 
		String encode = passwordEncoder.encode(user.getPassword());
		user.setPassword(encode);
		
		User  savedUser = this.userRepo.save(user);
		return savedUser;
	}

}
