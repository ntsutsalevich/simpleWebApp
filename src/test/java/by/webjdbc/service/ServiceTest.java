package by.webjdbc.service;

import by.webjdbc.dao.EmployeeDao;
import by.webjdbc.entity.Employee;
import by.webjdbc.entity.Gender;
import by.webjdbc.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.*;

public class ServiceTest {

    @InjectMocks
    EmployeeServiceImpl service;

    @Mock
    EmployeeDao dao;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        Employee employee = new Employee(1L, "Jon", "Snow",
                2L, "king", Gender.MALE, new Date(1988 - 10 - 11));
        Employee employee2 = new Employee(2L, "Cersei", "Lanister",
                1L, "queen", Gender.FEMALE, new Date(1970 - 10 - 11));
        Employee employee3 = new Employee(3L, "Sansa", "Stark",
                3L, "milady", Gender.FEMALE, new Date(1980 - 10 - 11));
        List<Employee> list = new ArrayList<>();
        Collections.addAll(list, employee, employee2, employee3);
        List<Employee> employees = service.findAll();
        when(dao.findAll()).thenReturn(list);
        verify(dao, times(1)).findAll();
    }
}
