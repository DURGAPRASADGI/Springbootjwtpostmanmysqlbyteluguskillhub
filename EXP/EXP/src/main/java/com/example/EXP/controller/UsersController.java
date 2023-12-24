package com.example.EXP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EXP.payLoad.JwtAuthenticator;
import com.example.EXP.payLoad.LoginDto;
import com.example.EXP.payLoad.UsersDto;
import com.example.EXP.security.JwtTokenProvider;
import com.example.EXP.service.UsersService;

@RestController
@RequestMapping("/api")
public class UsersController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/createUser")
	public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto){
		return new ResponseEntity<UsersDto>(usersService.createUser(usersDto),HttpStatus.CREATED);
		
	}
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticator> login(@RequestBody LoginDto loginDto){
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token= jwtTokenProvider.generteToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticator(token));
		
	}

}
