package com.alvaro.GestionTareas.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class TaskModel {

	private long id;
	private String title, description;
	private int taskStatus;
	private Date creationDate;
	private String employeeEmail;
	
	
	public boolean validate() {
		return !title.isEmpty() 
				&& title != null 
				&& !description.isEmpty() 
				&& description != null 
				&& taskStatus >= 0 
				&& taskStatus < 3
				&& !employeeEmail.isEmpty()
				&& employeeEmail != null;
	}
	
	//esta función valida solo el status. 
	public boolean validateTaskStatus() {
		return taskStatus >= 0 && taskStatus < 3;  
	}
}
