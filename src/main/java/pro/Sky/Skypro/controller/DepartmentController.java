package pro.Sky.Skypro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.Sky.Skypro.model.Employee;
import pro.Sky.Skypro.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/maxSalary")
    public Employee max(@RequestParam int department) {
        return service.findMaxSalary(department);
    }

    @GetMapping("/minSalary")
    public Employee min(@RequestParam int department) {
        return service.findminSalary(department);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> findAllByDepartment(@RequestParam int department){
        return service.findByDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> groupByDepartment() {
        return service.groupByDepartment();
    }
}