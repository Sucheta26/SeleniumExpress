package com.seleniumexpress.employeeapp.controller;

import com.seleniumexpress.employeeapp.entity.Employee;
import com.seleniumexpress.employeeapp.repo.EmployeeRepo;
import com.seleniumexpress.employeeapp.response.EmployeeResponse;
import com.seleniumexpress.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id)
    {
        //db call
        //Employee employee = employeeService.getEmployeeById(id);
        //return employee;

        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);

    }
}
