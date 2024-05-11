package pro.sky.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.exception.EmployeeAlreadyAddedException;
import pro.sky.exception.EmployeeNotFoundException;
import pro.sky.exception.EmployeeStorageIsFullException;
import pro.sky.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping()
    public String welcome(){
        return "Welcome";
    }

    @GetMapping("/add")
    public String add (@RequestParam(value = "firstName", required = false)String firstName,
                       @RequestParam(value = "lastName", required = false)String lastName){
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()){
            return "неверные параметры";
        }
        try {
        return employeeService.addEmployee(firstName,lastName);
        }
        catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e){
            return  e.getMessage();
        }
    }
    @GetMapping("/find")
    public String find (@RequestParam(value = "firstName", required = false)String firstName,
                       @RequestParam(value = "lastName", required = false)String lastName){
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()){
            return "неверные параметры";
        }
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e){
            return e.getMessage();
        }
    } @GetMapping("/remove")
    public String remove (@RequestParam(value = "firstName", required = false)String firstName,
                       @RequestParam(value = "lastName", required = false)String lastName){
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()){
            return "неверные параметры";
        }
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e){
            return e.getMessage();

        }
    }
    @GetMapping("/print")
    public String print (){
        return employeeService.printAllEmployee();
    }
}
