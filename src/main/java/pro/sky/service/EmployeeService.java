package pro.sky.service;

import org.springframework.stereotype.Service;
import pro.sky.exception.EmployeeAlreadyAddedException;
import pro.sky.exception.EmployeeNotFoundException;
import pro.sky.exception.EmployeeStorageIsFullException;
import pro.sky.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 10;
    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Пол", "Уокер"),
            new Employee("Вин", "Дизель"),
            new Employee("Джордана", "Брюстер"),
            new Employee("Тайриз", "Гибсон"),
            new Employee("Мишель", "Родригес"),
            new Employee("Джейсон", "Стейтем")
    ));


    public String addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException,
            EmployeeStorageIsFullException {
        if (employees.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Максимальное колличество сотрудников");
        }
        if (employees.contains(new Employee(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.add(new Employee(firstName, lastName));
        return employees.get(employees.indexOf(new Employee(firstName, lastName))).toString();
    }

    public String deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        int i = employees.indexOf(new Employee(firstName, lastName));
        if (i >= 0) {
            String delete = employees.get(employees.indexOf(new Employee(firstName,lastName))).toString();
            employees.remove(new Employee(firstName, lastName));
            return delete;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public String findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        if (employees.contains(new Employee(firstName, lastName))) {
            return employees.get(employees.indexOf(new Employee(firstName,lastName))).toString();
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
    public String printAllEmployee(){
        String list ="";
        for (int i = 0; i < employees.size(); i++) {
            list = list + employees.get(i).toString();
        }
        return list;

    }


}
