package com.venkatesh.SpringBootPlayground.security.unlogged_blog.repo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try(Connection connection = dataSource.getConnection()){
            Statement statement =  connection.createStatement();
            statement.executeUpdate("INSERT INTO employee  (name, email, department, company) VALUES ('Aarav Kumar', 'aarav.kumar@example.com', 'HR', 'Tech Innovations Pvt Ltd')");
            statement.executeUpdate("INSERT INTO employee (name, email, department, company) VALUES ('Diya Sharma', 'diya.sharma@example.com', 'Marketing', 'Creative Minds Ltd')");
            statement.executeUpdate("INSERT INTO employee (name, email, department, company) VALUES ('Rohan Gupta', 'rohan.gupta@example.com', 'Finance', 'Financial Solutions Inc')");
            statement.executeUpdate("INSERT INTO employee (name, email, department, company) VALUES ('Isha Patel', 'isha.patel@example.com', 'IT', 'Tech Solutions Pvt Ltd')");
            statement.executeUpdate("INSERT INTO employee (name, email, department, company) VALUES ('Aditya Singh', 'aditya.singh@example.com', 'Operations', 'Manufacturing Corp')");
        }
    }
}
