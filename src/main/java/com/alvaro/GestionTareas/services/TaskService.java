package com.alvaro.GestionTareas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvaro.GestionTareas.converters.TaskConverter;
import com.alvaro.GestionTareas.entities.Task;
import com.alvaro.GestionTareas.models.TaskModel;
import com.alvaro.GestionTareas.repositories.EmployeeRepository;
import com.alvaro.GestionTareas.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TaskConverter taskConverter;
	
	//Funcion para devolver todas las tareas.
	public List<TaskModel> getTasks(){
		
		List<Task> taskListEntities = taskRepository.findAll();
		
		ArrayList<TaskModel> taskListModels = new ArrayList();
		
		for (Task task : taskListEntities) {
			taskListModels.add(taskConverter.EntityToModel(task));
		}
		
		return taskListModels;
	}
	
	//Función para devolver las tareas por su status.
	public List<TaskModel> getTasksByStatus(int status){
		List<Task> taskListEntites = taskRepository.findByTaskStatus(status);
		
		ArrayList<TaskModel> taskListModels = new ArrayList();
		
		for (Task task : taskListEntites) {
			taskListModels.add(taskConverter.EntityToModel(task));
		}
		
		return taskListModels;
	}

	//Función para devolver las tareas de un empleado.
	public List<TaskModel> getTasksByEmail(String email) {
		
		List<Task> taskListEntities = taskRepository.findByEmployee_email(email);
		
		ArrayList<TaskModel> taskListModels = new ArrayList();
		
		for (Task task : taskListEntities) {
			taskListModels.add(taskConverter.EntityToModel(task));
		}
		
		return taskListModels;
	}
	
	//Función para agregar una tarea.
	public void addTask(TaskModel taskModel) {
		taskRepository.save(taskConverter.ModelToEntity(taskModel));
	}

	//Función para eliminar una tarea.
	public Optional<TaskModel> deleteTask(long id) {
		Optional <TaskModel> resultTaskModel = Optional.empty();
		Optional <Task> result = taskRepository.findById(id);
		
		if (result.isPresent()) {
			taskRepository.deleteById(id);
			return resultTaskModel.of(taskConverter.EntityToModel(result.get())); 
		}
		
		return resultTaskModel;
	}
	
	//Función para modificar el estatus de una tarea.
	public Optional<TaskModel> modifyTaskStatus(long id, int status){
		Optional <Task> oldTask = taskRepository.findById(id);
		Optional <TaskModel> newTask = Optional.empty();
		
		if(oldTask.isPresent()) {
			Task auxTask = oldTask.get();
			auxTask.setTaskStatus(status);
			taskRepository.save(auxTask);
			return newTask.of(taskConverter.EntityToModel(auxTask));
		}
		return newTask;
	}
}
