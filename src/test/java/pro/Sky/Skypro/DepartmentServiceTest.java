package pro.Sky.Skypro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.Sky.Skypro.exception.EmployeeNotFoundException;
import pro.Sky.Skypro.model.Employee;
import pro.Sky.Skypro.service.DepartmentService;
import pro.Sky.Skypro.service.EmployeeService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Set<Employee> employees = Set.of(
                new Employee("test", "test"),
                new Employee("test2", "test2"),
                new Employee("test3", "test3"),
                new Employee("test4", "test4"),
                new Employee("test5", "test5"),
                new Employee("test6", "test6")
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void testDepartmentMaxSalary() {
        assertThat(departmentService.findMaxSalary(1).equals(new Employee("test2", "test2"));
        assertThat(departmentService.findMaxSalary(3)).equals(new Employee("test6", "test6"));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMaxSalary(1111));
    }

    @Test
    void testDepartmentMinSalary() {
        var actual1 = departmentService.findminSalary(1);
        assertThat(actual1).isEqualTo(new Employee("test", "test"));

        var actual2 = departmentService.findminSalary(1);
        assertThat(actual2).isEqualTo(new Employee("test6", "test6"));

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findminSalary(1111));
    }

    @Test
    void testFindByDepartment() {
        var actual = departmentService.findByDepartment(2);
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("test3", "test3"),
                new Employee("test4", "test4"),
                new Employee("test5", "test5")
        );
    }

    @Test
    void testGroupByDepartment() {
        var actual = departmentService.groupByDepartment();
        var expected = Map.of(
                1, List.of(new Employee("test", "test"), new Employee("test2", "test2")),
                        2, List.of(new Employee("test3", "test3"), new Employee("test4", "test4")),
                                3, List.of(new Employee("test6", "test6"), new Employee("test5", "test5")
                                ));
        assertThat(actual).isEqualTo(expected);
    }
}
