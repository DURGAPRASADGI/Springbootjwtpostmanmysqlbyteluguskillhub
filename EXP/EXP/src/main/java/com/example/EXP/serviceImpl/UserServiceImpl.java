package com.example.EXP.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.EXP.entity.Users;
import com.example.EXP.payLoad.UsersDto;
import com.example.EXP.repository.UsersRepository;
import com.example.EXP.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ModelMapper modelMapper;

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public UsersDto createUser(UsersDto usersDto) {
		// TODO Auto-generated method stub
		Users users=modelMapper.map(usersDto, Users.class);
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		 Users savedUser= usersRepository.save(users);
		

		return modelMapper.map(savedUser, UsersDto.class);
	}

}
