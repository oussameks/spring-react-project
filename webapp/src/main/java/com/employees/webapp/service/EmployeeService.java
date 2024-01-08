package com.employees.webapp.service;

import com.employees.webapp.repository.Emprepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employees.webapp.model.Employee;
import com.employees.webapp.repository.EmployeeProxy;
import lombok.Data;
import java.util.Optional;
@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeProxy employeeProxy;

    @Autowired
    private Emprepo repo;

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public Employee getEmployeeById(Long id) { return employeeProxy.getEmployeeById(id); }

    public void deleteEmployee(Long id) {
        employeeProxy.deleteEmployee(id);
    }




}
		