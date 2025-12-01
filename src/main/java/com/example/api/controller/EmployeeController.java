package com.example.api.controller;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee fulfilled
     */

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    /**
     * Create - Add a new employee
     * @param employee An object employee
     * @return the employee object saved
     */
    @PostMapping("/add/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    /**
     * Read - Get one employee
     * @param id the id of the employee
     * @return An employee object fulfilled or null
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") final  Long id){
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee.orElse(null);
    }

    /**
     * Delete - delete an employee
     * @param id - the id of the employee to delete
     */
    @DeleteMapping("/delete/employee/{id}")
    public void deleteEmployee(final Long id){
        employeeService.deleteEmployee(id);
    }

    /**
     * Update - update an existing employee
     * @param id - the id of the employee to update
     * @param employee - the employee object updated
     * @return the employee updated
     */
    @PutMapping("/update/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee){
        Optional<Employee> emp = employeeService.getEmployee(id);

        if(emp.isPresent()){
            Employee updatedEmployee = emp.get();
            String  firstName = updatedEmployee.getFirstName();
            if(firstName != null){
                updatedEmployee.setFirstName(firstName);
            }
            String  lastName = updatedEmployee.getLastName();
            if(lastName != null){
                updatedEmployee.setLastName(lastName);
            }
            String mail = updatedEmployee.getMail();
            if(mail != null){
                updatedEmployee.setMail(mail);
            }
            String password = updatedEmployee.getPassword();
            if(password != null){
                updatedEmployee.setPassword(password);
            }
            return employeeService.save(updatedEmployee);
        }
        return null;
    }
}
