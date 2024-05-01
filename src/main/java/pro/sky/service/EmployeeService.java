package pro.sky.service;

import org.springframework.stereotype.Service;
import pro.sky.exception.EmployeeAlreadyAddedException;
import pro.sky.exception.EmployeeNotFoundException;
import pro.sky.exception.EmployeeStorageIsFullException;
import pro.sky.model.Employee;

import java.util.*;

@Service
public class EmployeeService {
    private final int MAX_EMPLOYEES = 10;
    private Map<String, Employee> employees;

    public EmployeeService(Map<String, Employee> employees) {
        this.employees = employee;/*Косяк здесь!*/

    }

    Map<String, Employee> employee = new HashMap<>(Map.of(
            "Пол Уокер",
            new Employee("Пол", "Уокер"),
            "Вин Дизель",
            new Employee("Вин", "Дизель"),
            "Джордана Брюстер",
            new Employee("Джордана", "Брюстер"),
            "Тайриз Гибсон",
            new Employee("Тайриз", "Гибсон")
    ));

    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException,
            EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(employee.getFullName());

    }


    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employee.getFullName());
    }

    public Collection<Employee> printAllEmployee() {
        return Collections.unmodifiableCollection(employees.values());
    }


}
