package pro.Sky.Skypro;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import pro.Sky.Skypro.exception.EmployeeAlreadyAddedException;
import pro.Sky.Skypro.exception.EmployeeNotFoundException;
import pro.Sky.Skypro.exception.EmployeeStoragelsFullException;
import pro.Sky.Skypro.model.Employee;
import pro.Sky.Skypro.service.EmployeeService;

class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void testAdd() {
        employeeService.add("test", "testtest", 1000, 1);
        employeeService.add("tEsTTEST", "teStteStest", 2000, 3);

        var actual1 = employeeService.find("test", "TESTTEST");
        assertThat(actual1).isNotNull();
        assertThat(actual1).getFirstName()).isEqualTo("Test");
        assertThat(actual1).getLastName()).isEqualTo("Testtest");
        assertThat(actual1).getDepartment()).isEqualTo(1);
        assertThat(actual1).getSalary()).isEqualTo(1000);

        var actual2 = employeeService.find("TESTTEST", "TESTTESTtest");
        assertThat(actual1).isNotNull();
        assertThat(actual1).getFirstName()).isEqualTo("Test");
        assertThat(actual1).getLastName()).isEqualTo("Testtest");
        assertThat(actual1).getDepartment()).isEqualTo(3);
        assertThat(actual1).getSalary()).isEqualTo(2000);

        @Test
        void testAddDuplicate () {
            employeeService.add("test", "testtest", 1000, 1);
            assertThrows(EmployeeAlreadyAddedException.class ->employeeService.add("test", "testtest", 2000, 3))
        }

        @Test
        void testFull () {
            employeeService.add("testt", "testtest", 1000, 1);
            employeeService.add("testtt", "testtest", 1000, 1);
            employeeService.add("testttt", "testtest", 1000, 1);
            employeeService.add("testtttt", "testtest", 1000, 1);
            employeeService.add("testttttt", "testtest", 1000, 1);
            employeeService.add("testtttttt", "testtest", 1000, 1);
            employeeService.add("testttttttt", "testtest", 1000, 1);
            employeeService.add("testtttttttt", "testtest", 1000, 1);
            employeeService.add("testtttttttttt", "testtest", 1000, 1);
            employeeService.add("testttttttttttt", "testtest", 1000, 1);
            assertThrows(EmployeeStoragelsFullException.class, () -> employeeService.add("testtest", "testtest", 1000, 1));
        }

        @Test
                void testWrongName() {
            assertThrows(WrongNameException.class, () -> employeeService.add("test11", "testtest", 1000, 1));
            assertThrows(WrongNameException.class, () -> employeeService.add("1test11", "testtest", 1000, 1));
            assertThrows(WrongNameException.class, () -> employeeService.add("2test11", "t2esttest", 1000, 1));
            assertThrows(WrongNameException.class, () -> employeeService.add("3test11", "testtest2", 1000, 1));
        }

        @Test
                void testGetAll() {
            employeeService.add("testt", "testtest", 1000, 1);
            employeeService.add("testtt", "testtestt", 2000, 3);

            var actual = employeeService.getAll();
            assertThat(actual).containsExactlyInAnyOrder(
                    new Employee("Testt", "Testtest",1000, 1),
                    new Employee("Testtw", "Testtestr",2000, 3)
            );
        }

        @Test
                void testNotFound() {
            assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("foo", "bar"));
        }

        @Test
                void testRemove() {
            assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove("foo", "bar"));

            employeeService.add("testt", "testtest", 1000, 1);
            employeeService.add("testttt", "testtest", 2000, 3);
            var actual = employeeService.find("testt", "testtest");
            assertThat(actual).isNotNull();
            employeeService.remove("testt", "testtest");
            assertThrow(EmployeeNotFoundException.class, () -> employeeService.find("test", "testtest"));
        }
    }
}
