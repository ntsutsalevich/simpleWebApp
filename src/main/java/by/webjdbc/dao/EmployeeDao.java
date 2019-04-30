package by.webjdbc.dao;

import by.webjdbc.entity.Employee;
import java.util.List;

public interface EmployeeDao {


    List<Employee> findAll();

    Employee findEmployeeById(Long id);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Long id);




}
