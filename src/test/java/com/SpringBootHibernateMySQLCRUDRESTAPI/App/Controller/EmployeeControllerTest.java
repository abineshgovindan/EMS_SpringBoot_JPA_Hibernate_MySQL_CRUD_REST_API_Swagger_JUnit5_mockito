package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Controller;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.DTO.EmployeeRequest;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.EMPType;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Employee;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Gender;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Services.ServiceImp.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import springfox.documentation.RequestHandler;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){

    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void saveEmployee() throws Exception {
      EmployeeRequest  empReq = EmployeeRequest.build(
                1L,
                "AB",
                "G",
                "AB@Gamil.com",
                25,
                Gender.MALE,
                EMPType.FULL_TIME,
                85000,
                "9999988887");
       Employee empoyee = Employee.build(
                1L,
                "AB",
                "G",
                "AB@Gamil.com",
                25,
                Gender.MALE,
                EMPType.FULL_TIME,
                85000,
                "9999988887");


            when(service.saveEmployee(any(EmployeeRequest.class))).thenReturn(empoyee);

        ResultActions res =  mvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empReq))
                .accept(MediaType.APPLICATION_JSON));

        res.andExpect(status().isCreated())
                .andExpect(jsonPath("$.age", CoreMatchers.is(empoyee.getAge())));

        empReq = null;
        empoyee =  null;

    }

    @Test

    void getAllEmployees() throws Exception {
       Employee empReq1 = Employee.build(
                1L,
                "AB",
                "G",
                "AB@Gamil.com",
                25,
                Gender.MALE,
                EMPType.FULL_TIME,
                85000,
                "9999988887");
        Employee empReq2 = Employee.build(
                2L,
                "ABC",
                "GF",
                "ABC@Gamil.com",
                24,
                Gender.FEMALE,
                EMPType.WORK_FROM_HOME,
                95000,
                "9999988880");
        List<Employee> employeeRequestList = new ArrayList<>();

        employeeRequestList.add(empReq1);
        employeeRequestList.add(empReq2);
        //when
        when(service.getAllEmployee()).thenReturn(employeeRequestList);

        mvc.perform(MockMvcRequestBuilders.get("/api/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(employeeRequestList.size())));





    }

    @Test
    void getEmployeeByID() throws Exception {
        Employee empReq1 = Employee.build(
                1L,
                "AB",
                "G",
                "AB@Gamil.com",
                25,
                Gender.MALE,
                EMPType.FULL_TIME,
                85000,
                "9999988887");
        //when
        when(service.getEmployeeByID(anyLong())).thenReturn(empReq1);

        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", empReq1.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.email", CoreMatchers.is(empReq1.getEmail())));


    }

    @Test
    void updateEmployee() throws Exception {
        EmployeeRequest  empReq = EmployeeRequest.build(
                1L,
                "AB",
                "G",
                "AB@Gamil.com",
                25,
                Gender.MALE,
                EMPType.FULL_TIME,
                85000,
                "9999988887");
        Employee empoyee = Employee.build(
                1L,
                "AB",
                "G",
                "AB@Gamil.com",
                25,
                Gender.MALE,
                EMPType.FULL_TIME,
                85000,
                "9999988887");


        when(service.updateEmployee(anyLong(),any(EmployeeRequest.class))).thenReturn(empoyee);

        ResultActions res =  mvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", empReq.getID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empReq))
                .accept(MediaType.APPLICATION_JSON));

        res.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.email", CoreMatchers.is(empoyee.getEmail())));


        empReq = null;
        empoyee =  null;

    }

    @Test
    void deleteEmployeeByID() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", anyLong())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }
}

//https://salithachathuranga94.medium.com/unit-and-integration-testing-in-spring-boot-micro-service-901fc53b0dff