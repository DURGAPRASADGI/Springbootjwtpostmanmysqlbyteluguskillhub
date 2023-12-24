package com.example.EXP.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EXP.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	 Optional<Users> findByEmail(String email);

}
