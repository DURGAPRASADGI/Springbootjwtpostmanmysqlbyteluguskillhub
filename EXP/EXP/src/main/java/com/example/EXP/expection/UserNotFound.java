package com.example.EXP.expection;

public class UserNotFound extends RuntimeException {
	private String message;

	public UserNotFound(String message) {
		super(message);
		this.message = message;
	}

}
