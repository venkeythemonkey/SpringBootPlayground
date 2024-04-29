package com.venkatesh.SpringBootPlayground.security.unlogged_blog.service;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.Employee;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.repo.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }
}
