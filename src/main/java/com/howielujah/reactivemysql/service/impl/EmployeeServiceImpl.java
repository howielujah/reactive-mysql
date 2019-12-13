package com.howielujah.reactivemysql.service.impl;

import com.howielujah.reactivemysql.dao.EmployeeRepository;
import com.howielujah.reactivemysql.entity.Employee;
import com.howielujah.reactivemysql.service.EmployeeService;
import com.howielujah.reactivemysql.util.CommonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Mono<Employee> create(Employee e) {
    return employeeRepository.save(e);
  }

  @Override
  public Mono<Employee> findById(Integer id) {
    return employeeRepository.findById(id);
  }

  public Flux<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Mono<Employee> update(Employee e) {
    Employee employee = employeeRepository.findById(e.getId()).block();
    String[] nullPropertyNames = CommonUtils.getNullPropertyNames(employee);
    BeanUtils.copyProperties(e, employee, nullPropertyNames);
    return employeeRepository.save(e);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return employeeRepository.deleteById(id);
  }

}
