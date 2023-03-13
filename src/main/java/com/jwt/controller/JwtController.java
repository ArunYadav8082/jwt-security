package com.jwt.controller;

import com.jwt.service.UserService;
import com.jwt.dao.UserRepository;
import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
	
	 @Autowired
	 private UserRepository userRepo;
	 @Autowired
	    private JwtUtil jwtUtility;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserService userService;

	   @GetMapping("/")
	    public String home() {
	        return "Welcome to Daily Code Buffer!!";
	    }

	    @PostMapping("/token")
	    public JwtResponse authenticate(@RequestBody @Valid JwtRequest jwtRequest) throws Exception{
	    	
	    	
	    	/*  List<com.jwt.model.User> user = this.userRepo.findAll();
			  
			  boolean userNam = user.stream().filter(x->x.getUsername()
					    .equalsIgnoreCase(jwtRequest.getUsername())).findFirst().isPresent();
			  
			  boolean pass = user.stream().filter(y->y.getPassword()
	                                 .equals(jwtRequest.getPassword())).findFirst().isPresent();

			  if(pass==true && userNam==true)
			  {*/
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            jwtRequest.getUsername(),
	                            jwtRequest.getPassword()
	                    )
	            );
	        } 
	        catch(UsernameNotFoundException e)
	        {
	        	 throw new Exception("INVALID_CREDENTIALS", e);
	        }
	        catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }

	        final UserDetails userDetails
	                = userService.loadUserByUsername(jwtRequest.getUsername());

	        final String token =
	                jwtUtility.generateToken(userDetails);
               System.out.println("******************"+token);
	        return  new JwtResponse(token);
	    }
			 /* else 
			    {
					 throw new Exception("User Not Found !!!");
			    	
			    }*/
			  
    // }
	 /*   else(Exception e)
	    {
	    	return e.printStackTrace();
	    	
	    }
	 */
}
