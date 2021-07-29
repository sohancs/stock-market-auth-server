package com.stockmarket.microservices.model;

import lombok.Data;

@Data
public class AuthRequestDTO {
	private String userName;
	private String password;
}
