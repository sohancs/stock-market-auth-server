package com.stockmarket.microservices.model;

import lombok.Data;

@Data
public class UserDetailsDTO {
	private String userName;
	private String password;
	private String email;
	private String role;
}
