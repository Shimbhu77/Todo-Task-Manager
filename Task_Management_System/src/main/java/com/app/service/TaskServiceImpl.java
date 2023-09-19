package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.exceptions.TaskException;
import com.app.exceptions.UserException;
import com.app.model.Task;
import com.app.model.TaskDTO;
import com.app.model.User;
import com.app.repository.TaskRepository;
import com.app.repository.UserRepository;

public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Task createTask(TaskDTO taskDTO) throws TaskException, UserException {
		
		User user =  userService.loginUser();
		
		if(user!=null)
		{
			Task task = new Task();
			
			task.setTitle(taskDTO.getTitle());
			task.setDescription(taskDTO.getDescription());
			task.setDutDate(taskDTO.getDutDate());
			task.setUser(user);
			
			taskRepository.save(task);
			
			return task;
		}
		
		throw new UserException("please login first");
	}

	@Override
	public Task updateTask(TaskDTO taskDTO, Integer taskId) throws TaskException, UserException {

		User user =  userService.loginUser();
		
		if(user!=null)
		{
			Optional<Task> optTask = taskRepository.findById(taskId);
			
			if(optTask.isPresent())
			{
				Task task = optTask.get();
				
				task.setTitle(taskDTO.getTitle());
				task.setDescription(taskDTO.getDescription());
				task.setDutDate(taskDTO.getDutDate());
				
				taskRepository.save(task);
				
				return task;
			}
			
			throw new TaskException("Task not found with this id : "+taskId);
			
			
		}
		
		throw new UserException("please login first");
	}

	@Override
	public String deleteTask(Integer taskId) throws TaskException, UserException {

		User user =  userService.loginUser();
		
		if(user!=null)
		{
			Optional<Task> optTask = taskRepository.findById(taskId);
			
			if(optTask.isPresent())
			{
				Task task = optTask.get();
				
				taskRepository.delete(task);
				
				return "task deleted successfully with this id "+task.getTaskId()+", title:  "+task.getTitle();
			}
			
			throw new TaskException("Task not found with this id : "+taskId);
					
		}
		
		throw new UserException("please login first");
	}

	@Override
	public String taskCompleted(Integer taskId) throws TaskException, UserException {
		
        User user =  userService.loginUser();
		
		if(user!=null)
		{
			Optional<Task> optTask = taskRepository.findById(taskId);
			
			if(optTask.isPresent())
			{
				Task task = optTask.get();
				
				task.setCompleted(true);
				
				taskRepository.save(task);
				
				return "Task marked as completed.";
			}
			
			throw new TaskException("Task not found with this id : "+taskId);	
			
		}
		
		throw new UserException("please login first");
	}

	@Override
	public String taskIncompleted(Integer taskId) throws TaskException, UserException {

		User user =  userService.loginUser();
		
		if(user!=null)
		{
			Optional<Task> optTask = taskRepository.findById(taskId);
			
			if(optTask.isPresent())
			{
				Task task = optTask.get();
				
				task.setCompleted(false);
				
				taskRepository.save(task);
				
				return "Task marked as incompleted.";
			}
			
			throw new TaskException("Task not found with this id : "+taskId);	
			
		}
		
		throw new UserException("please login first");
	}

}
