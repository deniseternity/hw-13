package pro.Sky.Skypro.service;

import org.springframework.stereotype.Service;
import pro.Sky.Skypro.exception.EmployeeNotFoundException;
import pro.Sky.Skypro.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee findminSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Collection<Employee> findByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
