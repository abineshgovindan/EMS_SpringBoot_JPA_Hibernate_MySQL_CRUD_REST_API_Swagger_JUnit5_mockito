package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Services;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.DTO.EmployeeRequest;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Expection.ResourceNotFoundExpection;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Employee;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Repository.EmployeeRepository;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Services.ServiceImp.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    EmployeeServiceImpl(){};
    EmployeeServiceImpl(EmployeeRepository repository){
        this.employeeRepository = repository;
    }

    @Override
    public Employee saveEmployee(EmployeeRequest employee) {
        Employee emp = Employee.build(0,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getGender(),
                employee.getEmpType(),
                employee.getSalary(),
                employee.getMobileNumber());
        return employeeRepository.save(emp);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByID(long id) {
        return employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExpection("Employee ID is not found","ID", id));
    }

    @Override
    public Employee updateEmployee(long id, EmployeeRequest employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExpection("Employee ID is not found","ID", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setEmpType(employee.getEmpType());



        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployeeByID(long id) {
        employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExpection("Employee ID is not found","ID", id));
        employeeRepository.deleteById(id);
    }

    @Override
    public void testMethode() {

        System.out.println(" -------------------------> "+ employeeRepository.findByemail("emma.johnson@example.com"));
    }
}
