package com.alvaro.GestionTareas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvaro.GestionTareas.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	public List<Task> findByTaskStatus(int status);
	
	public List<Task> findByEmployee_email(String email);
	
}
