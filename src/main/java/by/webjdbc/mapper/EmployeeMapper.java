package by.webjdbc.mapper;

import by.webjdbc.entity.Employee;
import by.webjdbc.entity.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {


    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee employee = new Employee(rs.getLong("employee_id"), rs.getString("first_name"),
                rs.getString("last_name"), rs.getLong("department_id"),
                rs.getString("job_title"), Gender.valueOf(rs.getString("gender")),
                rs.getDate("date_of_birth"));
        return employee;
    }
}
