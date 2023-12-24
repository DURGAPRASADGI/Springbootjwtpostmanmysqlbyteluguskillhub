package com.example.EXP.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.EXP.repository.UsersRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenicationFilter extends OncePerRequestFilter {
	
	@Autowired
	private SecurityAuth securityAuth;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get token
		String token=getToken(request);
 		//validate token
		if(StringUtils.hasText(token)&& jwtTokenProvider.validateToken(token)) {
			String email=jwtTokenProvider.getEmailFromToken(token);
			UserDetails userDetails=securityAuth.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		//Evaluate token
		filterChain.doFilter(request, response);
		
	}
	
	public String getToken(HttpServletRequest request) {
		String token= request.getHeader("Authorization");
		if(StringUtils.hasText(token)&& token.startsWith("Bearer")) {
			return token.substring(7, token.length());
		}
			
		return null;
		
	}

	
	
}
