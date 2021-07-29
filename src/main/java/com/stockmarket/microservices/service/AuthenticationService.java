package com.stockmarket.microservices.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stockmarket.microservices.model.User;
import com.stockmarket.microservices.model.UserDetailsDTO;
import com.stockmarket.microservices.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}

	public void registerUserService(UserDetailsDTO userDetailsDTO) {
		userRepository.save(mapToUserEntity(userDetailsDTO));
	}

	private User mapToUserEntity(UserDetailsDTO dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());
		return user;
	}

}
