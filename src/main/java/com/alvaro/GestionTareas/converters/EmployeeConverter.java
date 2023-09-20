package com.alvaro.GestionTareas.converters;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.alvaro.GestionTareas.entities.Employee;
import com.alvaro.GestionTareas.entities.Task;
import com.alvaro.GestionTareas.models.EmployeeModel;
import com.alvaro.GestionTareas.models.TaskModel;

@Component
public class EmployeeConverter {

	public EmployeeModel EntityToModel(Employee employee) {
		EmployeeModel employeeModel = new EmployeeModel();
		
		employeeModel.setEmail(employee.getEmail());
		employeeModel.setName(employee.getName());
		employeeModel.setLastName(employee.getLastName());
		employeeModel.setPassword("10€ al mes para ver las contraseñas...");
		
		return employeeModel;
	}
	
	public Employee ModelToEntity(EmployeeModel employeeModel) {
		Employee employee = new Employee();
		
		employee.setEmail(employeeModel.getEmail());
		employee.setName(employeeModel.getName());
		employee.setLastName(employeeModel.getLastName());
		employee.setPassword(employeeModel.getPassword());
		
		return employee;
	}
}
