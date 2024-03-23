package pro.Sky.Skypro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.Sky.Skypro.model.Employee;
import pro.Sky.Skypro.service.DepartmentService;
import pro.Sky.Skypro.service.EmployeeService;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService service, DepartmentService departmentService) {
        this.service = service;
        this.departmentService = departmentService;
    }

    @GetMapping("/add")
    public void add(String firstName, String lastName) {
        service.add(firstName, lastName);
    }

    @GetMapping("/delete")
    public void delete(String firstName, String lastName) {
        service.delete(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(String firstName, String lastName) {
        return service.find(firstName, lastName);
    }

    @GetMapping("/all")
    public Set<Employee> getAll() {
        return service.getAll();
    }
}