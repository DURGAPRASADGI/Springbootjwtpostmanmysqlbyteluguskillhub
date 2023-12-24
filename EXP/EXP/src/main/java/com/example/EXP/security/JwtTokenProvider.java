package com.example.EXP.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.EXP.expection.ApiNotFound;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
//	@Autowired
//	private Authentication authentication;
	
	
	private static final KeyPair screctKey=generatekey();
	
	public static KeyPair generatekey()  {
		// TODO Auto-generated method stub
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ec");
			return keyPairGenerator.generateKeyPair();
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("error key is not generted ");
		}
		
	}
	public String generteToken(Authentication authentication) {
		String email=authentication.getName();
		Date currentDate =new Date();
		Date ExpireDate =new Date(currentDate.getTime()+3600000);
		
		JwtBuilder jwtBuilder=Jwts.builder()
				              .setSubject(email)
				              .setIssuedAt(currentDate)
				              .setExpiration(ExpireDate)
				              .signWith(screctKey.getPrivate(), SignatureAlgorithm.ES256);
				              
		return jwtBuilder.compact();
	}

	public String getEmailFromToken(String token) {
		Claims claims=Jwts.parser()
				.setSigningKey(screctKey.getPublic())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
		
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(screctKey.getPublic())
			.build()
			.parseClaimsJws(token);
//			.getBody();
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiNotFound("Api has not found"+token);
		}
		
	}
	

	
}
