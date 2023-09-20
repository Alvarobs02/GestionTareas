package com.alvaro.GestionTareas.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "name", length = 50, nullable = true)
	private String name;
	
	@Column(name = "lastName", length = 50, nullable = true)
	private String lastName;
	
	@Column(name = "password", length = 50, nullable = true)
	private String password;
	
	@OneToMany (mappedBy = "employee")
	@JsonManagedReference
	private List<Task> tasks;
		
}
