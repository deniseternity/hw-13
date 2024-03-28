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
        Employee employees = List.of(
                new Employee("test", "test", 1000, 1),
                new Employee("test2", "test2", 10020, 1),
                new Employee("test3", "test3", 10030, 2),
                new Employee("test4", "test4", 10040, 3),
                new Employee("test5", "test5", 10050, 3),
                new Employee("test6", "test6", 10060, 3),
                );
        when(EmployeeService.getAll()).thenReturn((Set<Employee>) employees);
    }

    @Test
    void testDepartmentMaxSalary() {
        assertThat(departmentService.findMaxSalary(1).equals(new Employee("test2", "test2", 10020, 1));
        assertThat(departmentService.findMaxSalary(3)).equals(new Employee("test6", "test6", 10060, 3));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMaxSalary(1111));
    }

    @Test
    void testDepartmentMinSalary() {
        var actual1 = departmentService.findminSalary(1);
        assertThat(actual1.isPresent()).isTrue();
        assertThat(actual1.get().isEqualTo(new Employee("test", "test", 1000, 1)));

        var actual2 = departmentService.findminSalary(1);
        assertThat(actual2.isPresent()).isTrue();
        assertThat(actual2.get().isEqualTo(new Employee("test6", "test6", 10060, 3)));

        var actual3 = departmentService.findminSalary(1111);
        assertThat(actual3.isEmpty()).isTrue();
    }

    @Test
    void testFindByDepartment() {
        var actual = departmentService.findByDepartment(2);
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("test3", "test3", 10030, 2),
                new Employee("test4", "test4", 10040, 3),
                new Employee("test5", "test5", 10050, 3)
        );
    }

    @Test
    void testGroupByDepartment() {
        var actual = departmentService.groupByDepartment();
        var expected = Map.of(
                1, List.of(new Employee("test", "test, 1000, 1"), new Employee("test2", "test2", 10020, 1),
                        2, List.of(new Employee("test3", "test3, 10030, 2"), new Employee("test4", "test4", 10040, 3),
                                1, List.of(new Employee("test6", "test6, 100060, 3"), new Employee("test5", "test5", 10050, 3)
                                ));
        assertThat(actual).isEqualTo(expected);
    }
}
