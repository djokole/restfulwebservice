package com.udemy.webservices.restfulwebservice.user;


public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
