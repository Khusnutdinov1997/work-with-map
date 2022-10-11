package com.example.workwithmap.service;

import org.springframework.stereotype.Service;
import com.example.workwithmap.model.Employee;
import com.example.workwithmap.exception.EmployeeAlreadyAddedException;
import com.example.workwithmap.exception.EmployeeNotFoundException;
import com.example.workwithmap.exception.EmployeeStorageIsFullException;


import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final Map<String, Employee> employees;

    private final String ERR_EMPL_STORAGE_FULL = "Массив сотрудников переполнен";
    private final String ERR_EMPL_ALREADY_ADDED = "Сотрудник уже имеется в массиве";
    private final String ERR_EMPL_NOT_FOUND = "Сотрудник не найден";
    private final int MAX_EMPL_NUMBER = 10;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == MAX_EMPL_NUMBER) {
            throw new EmployeeStorageIsFullException(ERR_EMPL_STORAGE_FULL);
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException(ERR_EMPL_ALREADY_ADDED);
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException(ERR_EMPL_NOT_FOUND);
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException(ERR_EMPL_NOT_FOUND);
        }
    }

    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
