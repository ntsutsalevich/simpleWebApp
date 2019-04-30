package by.webjdbc.service;

import by.webjdbc.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findEmployeeById(Long id);

    void addEmployee(Employee employee);

    void updateEmployee( Employee employee);

    void deleteEmployee(Long id);


}
