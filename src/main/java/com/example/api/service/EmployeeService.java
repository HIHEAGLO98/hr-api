package com.example.api.service;

import java.util.Optional;

import com.example.api.model.Employee;
import com.example.api.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Optional<Employee> getEmployee(final Long id){
        return repository.findById(id);
    }

    public Iterable<Employee> getEmployees(){
        return repository.findAll();
    }

    public void deleteEmployee(final Long id){
        repository.deleteById(id);
    }

    public Employee save(final Employee employee){
        return repository.save(employee);
    }
}
