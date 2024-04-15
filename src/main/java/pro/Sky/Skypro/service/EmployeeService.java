package pro.Sky.Skypro.service;

import org.springframework.stereotype.Service;
import pro.Sky.Skypro.exception.EmployeeAlreadyAddedException;
import pro.Sky.Skypro.exception.EmployeeNotFoundException;
import pro.Sky.Skypro.exception.EmployeeStoragelsFullException;
import pro.Sky.Skypro.model.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class EmployeeService {

    private static final int MAX_COUNT = 5;
    private final Map<String, Employee> employeeMap= new HashMap<>(MAX_COUNT);

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        String key = makeKey(firstName, lastName);
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        if (employeeMap.size() >= MAX_COUNT) {
            throw new EmployeeStoragelsFullException();
        }
        Employee employee1 = employeeMap.put(key, employee);
        return employee1;
    }

    public void delete(String firstName, String lastName) {
        String key = makeKey(firstName, lastName);
        if (!employeeMap.containsKey(key)) {
            throw new EmployeeNotFoundException();
        } else {
            employeeMap.remove(key);
        }
    }

    public Employee find(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        if (employeeMap.containsKey(key)) {
            return employeeMap.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    public Set<Employee> getAll() {
        Set<Employee> allEmployee = new HashSet<>();
        allEmployee.addAll(employeeMap.values());
        return allEmployee;
    }

    private static String makeKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }
}
