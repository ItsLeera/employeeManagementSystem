package com.aptech.employeeManagementSystem.service.implementation;

import com.aptech.employeeManagementSystem.model.Employee;
import com.aptech.employeeManagementSystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    @Override
    public synchronized Employee createEmployee(Employee employee) {
        Employee emp = new Employee(nextId++ , employee.getName(), employee.getEmail());
        employees.add(emp);
        return emp;
    }

    @Override
    public synchronized List<Employee> listEmployee() {
        return List.copyOf(employees);
    }

    @Override
    public  Employee updateEmployee(Long id, Employee employee) {
        for(int index=0 ; index<employees.size(); index++){
            Employee existingEmployee = employees.get(index);

            if(existingEmployee.getId()==id){
                Employee updated = new Employee(existingEmployee.getId() , employee.getName(), employee.getEmail());
                employees.set(index,updated);
                return updated;
            }
        }
        throw employeeNotFound(id);
    }

    @Override
    public void  deleteEmployeeById(Long id) {
       boolean deleted = employees.removeIf(employee -> employee.getId()==id);
       if(!deleted){
           throw employeeNotFound(id);
       }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        for(Employee employee : employees){
            if(employee.getId() == id){
                return employee;
            }
        }
        throw employeeNotFound(id);
    }

    private ResponseStatusException employeeNotFound(Long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee with id " + id + " " + "was not found");
    }
}
