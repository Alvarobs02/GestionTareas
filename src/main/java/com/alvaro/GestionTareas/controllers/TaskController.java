package com.alvaro.GestionTareas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.GestionTareas.errors.AlreadyExitsTask;
import com.alvaro.GestionTareas.errors.BadRequestException;
import com.alvaro.GestionTareas.errors.NotFoundException;
import com.alvaro.GestionTareas.models.TaskModel;
import com.alvaro.GestionTareas.services.EmployeeService;
import com.alvaro.GestionTareas.services.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TaskController {


	@Autowired
	private TaskService taskService;
	
	@Autowired
	private EmployeeService employeeService;
	
	//Función para recuperar todas las tareas o una lista de tareas filtradas por el estado (Solo admin)
	@GetMapping(path = "/task")
	public List<TaskModel> getTasks(@RequestParam(name = "status", required = false)Integer status){		
		if (status == null) {
			return taskService.getTasks();
		} else if (status < 3 && status >= 0) {
			return taskService.getTasksByStatus(status);
		} else {
			throw new BadRequestException();
		}
	}
	
	//Funcion para recuperar todas las tareas de un solo usuario (Usuarios).
	@GetMapping(path = "/task/email/{email}")
	public List<TaskModel> getTasksByEmail(@PathVariable(name = "email")String email){
		if (employeeService.lookForEmployee(email)) {
			return taskService.getTasksByEmail(email);
		}
		throw new NotFoundException();
	}
	
	//Función para agregar una tarea (admin).
	@PostMapping(path = "/task")
	public void addTask(@RequestBody TaskModel taskModel) {
		if (!taskModel.validate()) {
			throw new BadRequestException();
		}
		
		//compruebo si el empleado existe
		if(employeeService.lookForEmployee(taskModel.getEmployeeEmail())) {
			taskService.addTask(taskModel);
		} else {
			throw new NotFoundException(); 
		}
	}
	
	//Función para eliminar una tarea (admin).
	@DeleteMapping(path = "/task/{id}")
	public TaskModel deleteTask(@PathVariable(name = "id") long id) {
		Optional <TaskModel> result = taskService.deleteTask(id);
		
		if (result.isPresent()) {
			return result.get();
		}
		throw new NotFoundException();
	}
	
	//Función para modificar una tarea (Empleado).
	@PutMapping(path = "/task")
	public TaskModel modifyTask(@RequestBody TaskModel taskModel) {
		if (!taskModel.validateTaskStatus()) {
			throw new BadRequestException();
		}
		
		Optional <TaskModel> result = taskService.modifyTaskStatus(taskModel.getId(), taskModel.getTaskStatus());
		if (result.isPresent()) {
			return result.get();
		}
		throw new NotFoundException();
	}
}
