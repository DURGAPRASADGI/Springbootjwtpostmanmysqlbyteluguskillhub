package com.example.EXP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EXP.payLoad.TasksDto;
import com.example.EXP.service.TasksService;

@RestController
@RequestMapping("/api")
public class TasksController {
	
	@Autowired
	private TasksService tasksService;
	
	@PostMapping("/createTask/{id}")
	public ResponseEntity<TasksDto> createTasks(@RequestBody TasksDto tasksDto,@PathVariable("id") long id){
		return new ResponseEntity<TasksDto>(tasksService.createTasks(tasksDto,id),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<TasksDto>> getTasks(@PathVariable("userId") long usersid){
		return new ResponseEntity<List<TasksDto>>(tasksService.getTasks(usersid),HttpStatus.OK);
		
	}

	@GetMapping(" /get/{userid}/{todoId}")
	public ResponseEntity<TasksDto> getSingleTask(@PathVariable("userid") long userid,@PathVariable("todoId") long todoId){
		return new ResponseEntity<TasksDto>(tasksService.getSingleTask(userid,todoId),HttpStatus.OK);
		
	}
	
}
