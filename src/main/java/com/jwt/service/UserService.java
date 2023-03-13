package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.dao.UserRepository;
import com.jwt.model.JwtRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService
{
	
	/*@Autowired
	 private JwtRequest jwtRequest;*/
	
	@Autowired
	 private UserRepository userRepo;

	 @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		 
	    com.jwt.model.User user = this.userRepo.findByUsername(userName);
	    
	 /*   System.out.println(user.getUsername()+" "+"UserName ! ! !  !! ! ! !");
	    System.out.println(user.getPassword()+" "+"Password ! ! !  !! ! ! !");*/
		  
          if(userName.equalsIgnoreCase(user.getUsername()))
        	  
            {
	        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
            }
            else
            {
            	return (UserDetails) new UsernameNotFoundException("User Not Found");
            }
	    }
}
