package com.example.EXP.service;

import java.util.List;

import com.example.EXP.payLoad.TasksDto;

public interface TasksService {

	TasksDto createTasks(TasksDto tasksDto,Long id);

	List<TasksDto> getTasks(long usersid);

	TasksDto getSingleTask(long userid, long todoId);


}
