package com.howielujah.reactivemysql.dao;

import com.howielujah.reactivemysql.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {

}
