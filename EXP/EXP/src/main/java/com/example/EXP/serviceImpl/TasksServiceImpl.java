package com.example.EXP.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EXP.entity.Tasks;
import com.example.EXP.entity.Users;
import com.example.EXP.expection.UserNotFound;
import com.example.EXP.payLoad.TasksDto;
import com.example.EXP.repository.TasksRepository;
import com.example.EXP.repository.UsersRepository;
import com.example.EXP.service.TasksService;

@Service
public class TasksServiceImpl implements TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public TasksDto createTasks(TasksDto tasksDto,Long id) {
		// TODO Auto-generated method stub
		
		Tasks tasks=modelMapper.map(tasksDto,Tasks.class);
		Users users=usersRepository.findById(id).orElseThrow(()-> new UserNotFound("user not found"+id));
		tasks.setUsers(users);
		Tasks savedTasks= tasksRepository.save(tasks);
		return modelMapper.map(savedTasks, TasksDto.class);
	}

	@Override
	public List<TasksDto> getTasks(long usersid) {
		// TODO Auto-generated method stub
		usersRepository.findById(usersid).orElseThrow(()-> new UserNotFound("user not found"+usersid));
         List<Tasks> list=tasksRepository.findAllByUsersId(usersid);
         
         
		
		return list.stream().map(task->modelMapper.map(task, TasksDto.class)).collect(Collectors.toList());
	}

	@Override
	public TasksDto getSingleTask(long userid, long todoId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
