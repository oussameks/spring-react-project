package com.employees.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.employees.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component

public class EmployeeProxy {
    @Autowired
    private CustomProperties props;
    /**
     * Get all employees
     * @return An iterable of all employees
     */
    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";
        System.out.println("EmployeeProxy *** getEmployees() getEmployeesUrl " +getEmployeesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                        getEmployeesUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Iterable<Employee>>() {}
                );
        System.out.println("EmployeeProxy *** getEmployees() response.getBody() " +response.getBody());
        log.debug("Get Employees call " + response.getStatusCode().toString());
        return response.getBody();
    }

    public Employee getEmployeeById(Long id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeByIdUrl = baseApiUrl + "/employees/" + id;
        System.out.println("EmployeeProxy *** getEmployeeById() getEmployeeByIdUrl " + getEmployeeByIdUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeByIdUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );
        System.out.println("EmployeeProxy *** getEmployeeById() response.getBody() " + response.getBody());
        return response.getBody();
    }

   /* public void deleteEmployee(Long id) {
        try {
            String baseApiUrl = props.getApiUrl();
            String deleteEmployeeUrl = baseApiUrl + "/employees/" + id;
            RestTemplate rt = new RestTemplate();
            rt.exchange(deleteEmployeeUrl, HttpMethod.DELETE, null, Employee.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new EmployeeNotFoundException("L'employé avec l'ID " + id + " n'existe pas.", ex);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.CONFLICT) {
                throw new EmployeeDeletionException("Impossible de supprimer l'employé avec l'ID " + id
                        + " en raison de conflits.", ex);
            }
            throw new EmployeeServiceException("Erreur lors de la suppression de l'employé avec l'ID " + id, ex);
        }
    }*/

    public void deleteEmployee(Long id){
        String baseApiUrl = props.getApiUrl();
        String deleteEmployee = baseApiUrl + "/deleteEmployee/" + id;
        System.err.println(deleteEmployee);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(deleteEmployee, HttpMethod.DELETE, null, Void.class);
    }
}
