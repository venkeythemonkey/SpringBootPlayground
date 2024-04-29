package com.venkatesh.SpringBootPlayground.security.unlogged_blog.repo;

import com.venkatesh.SpringBootPlayground.security.unlogged_blog.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}


/*
INSERT INTO employee  (name, email, department, company) VALUES ('Aarav Kumar', 'aarav.kumar@example.com', 'HR', 'Tech Innovations Pvt Ltd');
INSERT INTO employee (name, email, department, company) VALUES ('Diya Sharma', 'diya.sharma@example.com', 'Marketing', 'Creative Minds Ltd');
INSERT INTO employee (name, email, department, company) VALUES ('Rohan Gupta', 'rohan.gupta@example.com', 'Finance', 'Financial Solutions Inc');
INSERT INTO employee (name, email, department, company) VALUES ('Isha Patel', 'isha.patel@example.com', 'IT', 'Tech Solutions Pvt Ltd');
INSERT INTO employee (name, email, department, company) VALUES ('Aditya Singh', 'aditya.singh@example.com', 'Operations', 'Manufacturing Corp');

 */