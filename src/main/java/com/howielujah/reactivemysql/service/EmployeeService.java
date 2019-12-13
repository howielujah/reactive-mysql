package com.howielujah.reactivemysql.service;

import com.howielujah.reactivemysql.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
  Mono<Employee> create(Employee e);

  Mono<Employee> findById(Integer id);

  Flux<Employee> findAll();

  Mono<Employee> update(Employee e);

  Mono<Void> delete(Integer id);
}
