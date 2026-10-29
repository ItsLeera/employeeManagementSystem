package com.aptech.employeeManagementSystem.controller;

import com.aptech.employeeManagementSystem.model.Employee;
import com.aptech.employeeManagementSystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/employees/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> hello(){
        return employeeService.listEmployee();
    }

    @DeleteMapping("employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id , employee);
    }

    @GetMapping("employees/{id}")
    public Employee getSingleEmployee(@PathVariable Long id){
        return  employeeService.getEmployeeById(id);
    }
}

