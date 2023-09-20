package com.alvaro.GestionTareas.converters;

import org.springframework.stereotype.Component;

import com.alvaro.GestionTareas.entities.Employee;
import com.alvaro.GestionTareas.entities.Task;
import com.alvaro.GestionTareas.models.TaskModel;

@Component
public class TaskConverter {

	//Función para convertir una entidad de tarea a un modelo.
	public TaskModel EntityToModel(Task task) {
		TaskModel taskModel = new TaskModel();
		
		taskModel.setId(task.getId());
		taskModel.setTitle(task.getTitle());
		taskModel.setDescription(task.getDescription());
		taskModel.setTaskStatus(task.getTaskStatus());
		taskModel.setCreationDate(task.getCreationDate());
		taskModel.setEmployeeEmail(task.getEmployee().getEmail());
		
		return taskModel;
	}
	
	//Función para convertir un modelo de tarea a una entidad.
	public Task ModelToEntity(TaskModel taskModel) {
		Task task = new Task();
		Employee employee = new Employee();
		
		employee.setEmail(taskModel.getEmployeeEmail());

		task.setTitle(taskModel.getTitle());
		task.setDescription(taskModel.getDescription());
		task.setTaskStatus(taskModel.getTaskStatus());
		task.setCreationDate(taskModel.getCreationDate());
		task.setEmployee(employee);
		
		return task;
	}
}
