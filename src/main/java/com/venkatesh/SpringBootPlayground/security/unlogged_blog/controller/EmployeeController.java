package com.venkatesh.SpringBootPlayground.security.unlogged_blog.controller;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.Employee;
import com.venkatesh.SpringBootPlayground.security.unlogged_blog.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {


    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return service.findAll();
    }


    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }


}
