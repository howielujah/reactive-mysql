package com.howielujah.reactivemysql.controller;

import com.howielujah.reactivemysql.entity.Employee;
import com.howielujah.reactivemysql.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Employee> findAll() {
    return employeeService.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Employee> findById(@PathVariable("id") Integer id) {
    return employeeService.findById(id);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Employee> create(@RequestBody Employee e) {
    return employeeService.create(e);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Employee> update(@RequestBody Employee e) {
    return employeeService.update(e);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> delete(@PathVariable("id") Integer id) {
    return employeeService.delete(id);
  }

}
