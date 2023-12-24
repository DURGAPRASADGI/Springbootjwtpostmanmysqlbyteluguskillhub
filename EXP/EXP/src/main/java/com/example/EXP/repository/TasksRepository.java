package com.example.EXP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EXP.entity.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

	public List<Tasks> findAllByUsersId(long usersid);

}
