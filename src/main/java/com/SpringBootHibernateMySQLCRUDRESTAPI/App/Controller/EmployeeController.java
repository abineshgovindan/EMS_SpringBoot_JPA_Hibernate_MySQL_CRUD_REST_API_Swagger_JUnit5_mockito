package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Controller;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.DTO.EmployeeRequest;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Employee;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Services.ServiceImp.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@Tag(name = "Employee REST Controller",
        description ="This is the class implements all the CRUD api operation." )
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public EmployeeController(){};


    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created "
    )
    @Operation(summary = "Insert new Employee.")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequest employee){
        return  new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @Operation(summary = "Get all employees as a list.",
            description = "Get all employees as a list from DB."
     )
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return  new ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get employee based on the id.",
            description = "Get employee based on the id from  the DB.")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable long id){
        employeeService.testMethode();

        return new ResponseEntity<Employee>( employeeService.getEmployeeByID(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Operation( summary = "Update employee data based on the id.",
            description = "Update employee data based on the id in the DB.")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created "
    )
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody @Valid EmployeeRequest employee){

        return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete employee based on the id.",
            description = "Delete employee based on the id in the DB.")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    public ResponseEntity<String> deleteEmployeeByID(@PathVariable("id") long id){
        employeeService.deleteEmployeeByID(id);
        return new ResponseEntity<String>("EmployeeID : " +id +" ->  is delete  ", HttpStatus.ACCEPTED);
    }




}
