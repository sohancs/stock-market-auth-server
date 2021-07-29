package com.stockmarket.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.microservices.exception.InvalidUserException;
import com.stockmarket.microservices.jwt.JwtUtil;
import com.stockmarket.microservices.model.AuthRequestDTO;
import com.stockmarket.microservices.model.UserDetailsDTO;
import com.stockmarket.microservices.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/registerUser")
	public ResponseEntity<Object> registerUser(@RequestBody UserDetailsDTO userDetailsDTO) {
		authService.registerUserService(userDetailsDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/isAutheticateUser")
	public ResponseEntity<Object> isAutheticateUser(@RequestBody AuthRequestDTO authDTO) throws InvalidUserException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassword()));
		} catch (Exception ex) {
			throw new InvalidUserException("Invalid username/password for user : "+authDTO.getUserName());
		}
		return ResponseEntity.ok(jwtUtil.generateToken(authDTO.getUserName()));
	}
	
	@GetMapping("/testAuthServerWithJwt")
	public ResponseEntity<Object> testAuthServerWithJwt() {
		return ResponseEntity.ok().body("Jwt security working as expected.");
	}
}
