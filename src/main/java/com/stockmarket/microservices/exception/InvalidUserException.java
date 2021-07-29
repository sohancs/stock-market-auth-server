package com.stockmarket.microservices.exception;

public class InvalidUserException extends RuntimeException {
	
	public InvalidUserException(String message) {
		super(message);
	}
}
