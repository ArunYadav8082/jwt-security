package com.jwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.User;
import com.jwt.service.SavedUserService;

@RestController
public class UserController {
	
	@Autowired
	private SavedUserService savedUserService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user)
	{
	  // this.savedUserService.saveUser(user);
		
		return new ResponseEntity(this.savedUserService.saveUser(user),HttpStatus.CREATED);
       
	  
	}

}
