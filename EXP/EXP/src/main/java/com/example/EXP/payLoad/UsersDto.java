package com.example.EXP.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
	
	private long id;
	private String name;
	private String email;
	private String password;
	
}
