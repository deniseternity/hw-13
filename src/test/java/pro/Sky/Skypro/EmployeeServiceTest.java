package pro.Sky.Skypro;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import pro.Sky.Skypro.exception.EmployeeAlreadyAddedException;
import pro.Sky.Skypro.exception.EmployeeNotFoundException;
import pro.Sky.Skypro.exception.EmployeeStoragelsFullException;
import pro.Sky.Skypro.exception.WrongNameException;
import pro.Sky.Skypro.model.Employee;
import pro.Sky.Skypro.service.EmployeeService;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void testAdd() {

        EmployeeService employeeService = new EmployeeService();

        employeeService.add("test", "testtest");
        employeeService.add("tEsTTEST", "teStteStest");

        var actual1 = employeeService.find("test", "TESTTEST");

        assertThat(actual1).isNotNull();
        assertThat(actual1.getFirstName()).isEqualTo("Test");
        assertThat(actual1.getLastName()).isEqualTo("Testtest");
        assertThat(actual1.getDepartment()).isEqualTo(1);
        assertThat(actual1.getSalary()).isEqualTo(1000);
}

        @Test
        void testAddDuplicate() {
            employeeService.add("test", "testtest");
            assertThrows(EmployeeAlreadyAddedException.class, () ->employeeService.add("test", "testtest"));
        }

        @Test
        void testFull () {
            employeeService.add("testt", "testtest");
            employeeService.add("testtt", "testtest");
            employeeService.add("testttt", "testtest");
            employeeService.add("testtttt", "testtest");
            employeeService.add("testttttt", "testtest");
            employeeService.add("testtttttt", "testtest");
            employeeService.add("testttttttt", "testtest");
            employeeService.add("testtttttttt", "testtest");
            employeeService.add("testtttttttttt", "testtest");
            employeeService.add("testttttttttttt", "testtest");
            assertThrows(EmployeeStoragelsFullException.class, () -> employeeService.add("testtest", "testtest"));
        }

        @Test
                void testWrongName() {
            assertThrows(WrongNameException.class, () -> employeeService.add("test11", "testtest"));
            assertThrows(WrongNameException.class, () -> employeeService.add("1test11", "testtest"));
            assertThrows(WrongNameException.class, () -> employeeService.add("2test11", "t2esttest"));
            assertThrows(WrongNameException.class, () -> employeeService.add("3test11", "testtest2"));
        }

        @Test
                void testGetAll() {
            employeeService.add("testt", "testtest" );
            employeeService.add("testtt", "testtestt");

            var actual = employeeService.getAll();
            assertThat(actual).containsExactlyInAnyOrder(
                    new Employee("Testt", "Testtest"),
                    new Employee("Testtw", "Testtestr")
            );
        }

        @Test
                void testNotFound() {
            assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("foo", "bar"));
        }

        @Test
                void testRemove() {
            assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete("foo", "bar"));

            employeeService.add("testt", "testtest");
            employeeService.add("testttt", "testtest");
            var actual = employeeService.find("testt", "testtest");
            assertThat(actual).isNotNull();
            employeeService.delete("testt", "testtest");
            assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("test", "testtest"));
        }
    }
