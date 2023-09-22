package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.TaskException;
import com.app.exceptions.UserException;
import com.app.model.Task;
import com.app.model.TaskDTO;
import com.app.model.User;
import com.app.service.TaskService;
import com.app.service.UserService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/app/create-task")
	public ResponseEntity<Task> addTaskHandler(@RequestBody TaskDTO taskDTO) throws TaskException, UserException
	{
		Task task = taskService.createTask(taskDTO);
		
		return new ResponseEntity<Task>(task,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/app/update-task/{taskId}")
	public ResponseEntity<Task> updateTaskHandler(@RequestBody TaskDTO taskDTO, @PathVariable("taskId") Integer taskId) throws TaskException, UserException
	{
		Task task = taskService.updateTask(taskDTO, taskId);
		
		return new ResponseEntity<Task>(task,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/app/delete-task/{taskId}")
	public ResponseEntity<String> deleteTaskHandler(@PathVariable("taskId") Integer taskId) throws TaskException, UserException
	{
		String message = taskService.deleteTask(taskId);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/app/mark-task-as-completed/{taskId}")
	public ResponseEntity<String> markTaskCompletedHandler(@PathVariable("taskId") Integer taskId) throws TaskException, UserException
	{
		String message = taskService.taskCompleted(taskId);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/app/mark-task-as-incompleted/{taskId}")
	public ResponseEntity<String> markTaskIncompletedHandler(@PathVariable("taskId") Integer taskId) throws TaskException, UserException
	{
		String message = taskService.taskIncompleted(taskId);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/app/tasks/search/{keyword}")
	public ResponseEntity<List<Task>> searchTaskByTitleOrDescriptionHandler(@PathVariable("keyword") String keyword) throws TaskException, UserException
	{
		List<Task> tasks = taskService.searchTaskByTitleOrDescription(keyword);
		
		return new ResponseEntity<List<Task>>(tasks,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/app/my-tasks/")
	public ResponseEntity<List<Task>> findMyAllTasksHandler() throws TaskException, UserException
	{
		User user =  userService.loginUser();
		
		if(user!=null)
		{
			List<Task> tasks = taskService.findAllTaskByUser(user.getUserId());
			
			return new ResponseEntity<List<Task>>(tasks,HttpStatus.ACCEPTED);
		}
		
		throw new UserException("please login first");
		
	}
	
//	@GetMapping("/app/tasks/filter-by-due-date")
//	public ResponseEntity<List<Task>> filterTaskBasedOnDuedateHandler(@PathVariable("dueDate") LocalDate dueDate) throws TaskException, UserException
//	{
//	
//		List<Task> tasks = taskService.filterTaskByDueDate(dueDate);
//		
//		return new ResponseEntity<List<Task>>(tasks,HttpStatus.ACCEPTED);
//		
//	}
	
	@GetMapping("/app/tasks/filter-completed-tasks")
	public ResponseEntity<List<Task>> filterCompletedTaskHandler() throws TaskException, UserException
	{
		
		List<Task> tasks = taskService.filterCompletedTask();
		
		return new ResponseEntity<List<Task>>(tasks,HttpStatus.ACCEPTED);
		
	}
}
