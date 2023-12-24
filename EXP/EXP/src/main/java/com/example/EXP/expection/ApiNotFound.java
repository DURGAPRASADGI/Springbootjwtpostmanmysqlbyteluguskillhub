package com.example.EXP.expection;

public class ApiNotFound extends RuntimeException {
	private String message;

	public ApiNotFound(String message) {
		super();
		this.message = message;
	}
	

}
