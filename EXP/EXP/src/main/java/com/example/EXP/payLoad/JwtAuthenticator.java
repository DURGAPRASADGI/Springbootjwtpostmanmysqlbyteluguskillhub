package com.example.EXP.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtAuthenticator {
	
	private String token;
	private String tokenType="Bearer";
	public JwtAuthenticator(String token) {
		super();
		this.token = token;
	}

}
