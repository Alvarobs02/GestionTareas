package com.alvaro.GestionTareas.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Task")
public class Task {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "title", length = 50, nullable = true)
	private String title;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "taskStatus")
	private int taskStatus;
	
	@Column(name = "creationDate")
	private Date creationDate;
	
	@ManyToOne
	@JsonBackReference
	private Employee employee;
	
}
