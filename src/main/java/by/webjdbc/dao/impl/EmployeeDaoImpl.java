package by.webjdbc.dao.impl;

import by.webjdbc.dao.EmployeeDao;
import by.webjdbc.entity.Employee;
import by.webjdbc.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {


    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }


    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees ORDER by employee_id";
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public Employee findEmployeeById(Long id) {
        String sql = "SELECT * FROM employees WHERE employee_id=?";
        return jdbcTemplate.queryForObject(sql, new EmployeeMapper(), id);
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (first_name,last_name,department_id,job_title,gender,date_of_birth) " +
                "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender().toString(), employee.getDateOfBirth());
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, department_id=?, job_title=?," +
                "gender=?,date_of_birth=? WHERE employee_id=?";
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
                employee.getGender().toString(), employee.getDateOfBirth(), employee.getEmployeeId());
    }

    @Override
    public void deleteEmployee(Long id) {
        String sql = "DELETE FROM employees WHERE employee_id=?";
        jdbcTemplate.update(sql, id);
    }


}
