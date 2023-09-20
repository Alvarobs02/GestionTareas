package com.alvaro.GestionTareas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.GestionTareas.errors.AlreadyExistEmployee;
import com.alvaro.GestionTareas.errors.BadRequestException;
import com.alvaro.GestionTareas.models.EmployeeModel;
import com.alvaro.GestionTareas.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmpleadoController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//Función para consultar los empleados (Solo el admin)
	@GetMapping(path = "/employee")
	public List<EmployeeModel> getEmployees(){
		
		List<EmployeeModel> employeeList = employeeService.getEmployees();
		
		return employeeList;
	}
	
	//Funcion para dar de alta a un empleado (Empleados)
	@PostMapping(path = "/employee")
	public void addEmployee(@RequestBody EmployeeModel employeeModel) {
		if (!employeeModel.validate()) {
			throw new BadRequestException();
		}
		
		if(!employeeService.addEmployee(employeeModel)) {
			throw new AlreadyExistEmployee();
		}
	}
}
