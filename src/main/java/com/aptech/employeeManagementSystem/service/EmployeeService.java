package com.aptech.employeeManagementSystem.service;

import com.aptech.employeeManagementSystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> listEmployee();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployeeById(Long id);
    Employee getEmployeeByEmail(String email);
    Employee getEmployeeById(Long id);


}
