package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Services;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.DTO.EmployeeRequest;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.EMPType;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Employee;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Gender;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    @Autowired
    private EmployeeRepository repository;

    @InjectMocks
    @Autowired
    private EmployeeServiceImpl service;
    @Captor
    private ArgumentCaptor<Employee> captor;

    @BeforeEach
    void setUp() {
        service = new EmployeeServiceImpl(repository);

    }

    @AfterEach
    void tearDown() {
        captor = null;
        service = null;
    }

    @Test
    void canAddEmployee() {
        EmployeeRequest empReq = EmployeeRequest.build(
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
        service.saveEmployee(empReq);
        //then repository invoked -> capture the arg
        verify(repository).save(captor.capture());

        Employee res = captor.getValue();

        assertEquals(empReq.getEmail(), res.getEmail());

    }

    @Test
    void canGetAllEmployee() {
        service.getAllEmployee();

        //then
        verify(repository).findAll();
    }

    @Test
    @Disabled
    void getEmployeeByID() {

        //given
        Employee empReq = Employee.build(
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
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(empReq));
        Employee res = service.getEmployeeByID(1L);
        verify(repository).findById(anyLong());

        assertEquals(empReq, res);
        assertEquals(1L,captor.getValue());
    }

    @Test
    @Disabled
    void updateEmployee() {

    }

    @Test
    @Disabled
    void deleteEmployeeByID() {

    }
}