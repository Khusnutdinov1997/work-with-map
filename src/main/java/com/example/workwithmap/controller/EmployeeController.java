package com.example.workwithmap.controller;

import com.example.workwithmap.model.Employee;
import com.example.workwithmap.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {
        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping(path = "/add")
        public Object addEmployee(
                @RequestParam(value = "firstName") String firstName,
                @RequestParam(value = "lastName") String lastName) {
            Employee employee = null;
            try {
                employee = employeeService.addEmployee(firstName, lastName);
            } catch (Throwable e) {
                return e.getMessage();
            }
            return employee;
        }

        @GetMapping(path = "/remove")
        public Object removeEmployee(
                @RequestParam(value = "firstName") String firstName,
                @RequestParam(value = "lastName") String lastName) {
            Employee employee = null;
            try {
                employee = employeeService.removeEmployee(firstName, lastName);
            } catch (Throwable e) {
                return e.getMessage();
            }
            return employee;
        }

        @GetMapping(path = "/find")
        public Object findEmployee(
                @RequestParam(value = "firstName") String firstName,
                @RequestParam(value = "lastName") String lastName) {
            Employee employee = null;
            try {
                employee = employeeService.findEmployee(firstName, lastName);
            } catch (Throwable e) {
                return e.getMessage();
            }
            return employee;
        }

        @GetMapping(path = "/print")
        public Object printEmployees() {
            Collection<Employee> employees = null;
            try {
                employees = employeeService.printEmployees();
            } catch (Throwable e) {
                return e.getMessage();
            }
            return employees;
        }
}
