package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Services.ServiceImp;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.DTO.EmployeeRequest;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee saveEmployee(EmployeeRequest employee);
    public List<Employee> getAllEmployee();
    public Employee getEmployeeByID(long id);
    public Employee updateEmployee(long id, EmployeeRequest employee);
    public  void deleteEmployeeByID(long id);
    public void testMethode();
}
