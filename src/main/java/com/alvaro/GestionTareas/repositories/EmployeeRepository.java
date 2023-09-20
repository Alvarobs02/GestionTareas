package com.alvaro.GestionTareas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvaro.GestionTareas.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
