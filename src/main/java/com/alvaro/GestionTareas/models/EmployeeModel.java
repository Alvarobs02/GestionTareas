package com.alvaro.GestionTareas.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeModel {
	
	private String email, name, lastName, password;
	
	//Falta poner un regex para el password.
	public boolean validate(){
		return email.matches("^[_A-Za-z0-9-\\\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") 
				&& !name.isEmpty() 
				&& name != null 
				&& !lastName.isEmpty() 
				&& lastName != null 
				&& !password.isEmpty() 
				&& password != null;
	}
}
