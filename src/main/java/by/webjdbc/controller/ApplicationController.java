package by.webjdbc.controller;


import by.webjdbc.entity.Employee;
import by.webjdbc.entity.Gender;
import by.webjdbc.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    public String main(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);
        return "main";
    }

    @GetMapping("/addEmployee")
    public String addEmployeePage() {
        return "addEmployee";
    }

    @PostMapping("/findById")
    public String getById(@RequestParam Long employeeId, Model model) {
        if (employeeId != null) {
            Employee employeeById = employeeService.findEmployeeById(employeeId);
            model.addAttribute("employee", employeeById);
            return "employee";
        } else {
        return "redirect:main";
    }
    }

        @PostMapping("/addEmployee")
        public String addEmployee (@RequestParam String firstName, @RequestParam String lastName, @RequestParam
                Long departmentId, @RequestParam String jobTitle, @RequestParam String gender,
                @RequestParam String dateOfBirth){
            Gender maleOrFemale;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            try {
                date = df.parse(dateOfBirth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (gender.equals("MALE")) {
                maleOrFemale = Gender.MALE;
            } else {
                maleOrFemale = Gender.FEMALE;
            }
            Employee employee = new Employee(firstName, lastName, departmentId, jobTitle, maleOrFemale, date);
            employeeService.addEmployee(employee);
            return "redirect:/main";
        }

        @GetMapping("/delete/{employee_id}")
        public String deleteEmployee (@PathVariable("employee_id") Long employee_id){
            employeeService.deleteEmployee(employee_id);
            return "redirect:/main";
        }


        @GetMapping("/update/{employee_id}")
        public String updateEmployee (@PathVariable("employee_id") Long employee_id, Model model){
            Employee employeeById = employeeService.findEmployeeById(employee_id);
            model.addAttribute("employee", employeeById);
            return "updateEmployee";
        }

        @PostMapping("/updateEmployee")
        public String updateEmployee (@ModelAttribute("employee") Employee employee){

            employeeService.updateEmployee(employee);
            return "redirect:/main";
        }


    }
