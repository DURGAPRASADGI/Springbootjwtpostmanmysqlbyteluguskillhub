package com.example.EXP.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.EXP.entity.Users;
import com.example.EXP.expection.UserNotFound;
import com.example.EXP.repository.UsersRepository;

@Service
public class SecurityAuth implements UserDetailsService  {
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users=usersRepository.findByEmail(email).orElseThrow(()->new UserNotFound("user not found for this email"+email));
		Set<String> roles=new HashSet<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
	return new User(users.getEmail(), users.getPassword(), grandAuthorites(roles));

	}

	public Collection<? extends GrantedAuthority>  grandAuthorites(Set<String> roles) {
		return roles.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		
	}
}
