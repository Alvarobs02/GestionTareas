package com.alvaro.GestionTareas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvaro.GestionTareas.converters.EmployeeConverter;
import com.alvaro.GestionTareas.entities.Employee;
import com.alvaro.GestionTareas.models.EmployeeModel;
import com.alvaro.GestionTareas.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeConverter employeeConverter;
	
	//Función para devolver a los empleados.
	public List<EmployeeModel> getEmployees(){
		
		List<Employee> employeeListEntities = employeeRepository.findAll();
		
		ArrayList<EmployeeModel> employeeListModel = new ArrayList();
		
		for (Employee employee : employeeListEntities) {
			employeeListModel.add(employeeConverter.EntityToModel(employee));
		}
		
		return employeeListModel;
	} 
	
	//Función para agregar un empleado.
	public boolean addEmployee(EmployeeModel employeeModel) {
		
		if (!employeeRepository.findById(employeeModel.getEmail()).isPresent()) {
			employeeRepository.save(employeeConverter.ModelToEntity(employeeModel));
			return true;
		}
		return false;
	}
	
	//Función para buscar un empleado por su email
	public boolean lookForEmployee(String email) {
		return employeeRepository.findById(email).isPresent();
	}
}
