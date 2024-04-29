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

    @PostMapping("/addEmployee")
    public String addEmployee(){
        return "Employee added by ADMIN";
    }

    @GetMapping("/employee")
    public String whatemployee() {
        return "This /employee end point can be accessed by MANAGER";
    }

    @GetMapping("/employee-task")
    public String employeeTask(){
        return "This can be accessed by both MANAGER and EMPLOYEE";
    }


}
