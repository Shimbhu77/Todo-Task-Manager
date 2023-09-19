package com.app.service;

import com.app.exceptions.TaskException;
import com.app.exceptions.UserException;
import com.app.model.Task;
import com.app.model.TaskDTO;

public interface TaskService {

	 public Task createTask(TaskDTO taskDTO) throws TaskException, UserException;
	 public Task updateTask(TaskDTO taskDTO,Integer taskId) throws TaskException, UserException;
	 public String deleteTask(Integer taskId) throws TaskException, UserException;
	 public String taskCompleted(Integer taskId) throws TaskException, UserException;
	 public String taskIncompleted(Integer taskId) throws TaskException, UserException;
}
