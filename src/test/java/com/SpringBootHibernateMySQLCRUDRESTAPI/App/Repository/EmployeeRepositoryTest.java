package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Repository;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.EMPType;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Employee;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;


    //given
    //when
    //then

    @Test
    @Tag("SaveEmpObj")
    public void givenEmployeeObject_whenEmployeeSave_thenReturnTheObject(){
      //given
       Employee employee = Employee.builder()
                .id(1)
                .firstName("AB")
                .lastName("G")
                .email("AB@gmail.com")
                .age(23)
                .gender(Gender.MALE)
                .empType(EMPType.PART_TIME)
                .salary(25000)
                .mobileNumber("9999999999").build();

        repository.save(employee);
        //when

       Employee isStored = repository.findByemail("AB@gmail.com");
       //then
        System.out.println("ID ----------------> "+isStored.getId());
       assertEquals(isStored.getEmail(), employee.getEmail());

    }

    @Test
    @Tag("GetEmpByID")
    public void givenEmployeeID_whenCheckEmployeeObjInDB_thenReturnEmployeeObj(){
        Employee employee = Employee.builder()
                .id(1)
                .firstName("AB")
                .lastName("G")
                .email("AB@gmail.com")
                .age(23)
                .gender(Gender.MALE)
                .empType(EMPType.PART_TIME)
                .salary(25000)
                .mobileNumber("9999999999").build();

        repository.save(employee);
        Optional<Employee> isStored = repository.findById(Long.valueOf(1));
        assertEquals(1, isStored.get().getId());

    }
    @Test
    @Tag("UpdateEmpByID")
    public void givenEmployeeID_whenUpdateEmployeeByID_thenReturnEmployeeObj(){
        Employee  employee = Employee.builder()
                .id(1)
                .firstName("AB")
                .lastName("G")
                .email("AB@gmail.com")
                .age(23)
                .gender(Gender.MALE)
                .empType(EMPType.PART_TIME)
                .salary(25000)
                .mobileNumber("9999999999").build();

        repository.save(employee);
        Optional<Employee> isStored = repository.findById(Long.valueOf(1));
        isStored.get().setEmail("AAAA@gmail.com");
        repository.save(isStored.get());
        Optional<Employee> retriveObj = repository.findById(Long.valueOf(1));
        System.out.println("----------------------> "+retriveObj.get().getEmail());
        assertEquals("AAAA@gmail.com", retriveObj.get().getEmail());
    }

    @Test
    @Tag("GetEmployeeList")
    public void givenEmployeeList_whenGetEmployeeList_thenReturnEmployeList(){
        //Emloyee List
        Employee employee1 = Employee.builder()
                .id(1)
                .firstName("AB")
                .lastName("G")
                .email("AB1@gmail.com")
                .age(23)
                .gender(Gender.MALE)
                .empType(EMPType.PART_TIME)
                .salary(25000)
                .mobileNumber("9999999991").build();

        Employee employee2 = Employee.builder()
                .id(2)
                .firstName("ABC")
                .lastName("G")
                .email("AB2@gmail.com")
                .age(25)
                .gender(Gender.OTHERS)
                .empType(EMPType.FULL_TIME)
                .salary(25000)
                .mobileNumber("9999999988").build();

        Employee employee3 = Employee.builder()
                .id(3)
                .firstName("ABCD")
                .lastName("G")
                .email("AB3@gmail.com")
                .age(27)
                .gender(Gender.FEMALE)
                .empType(EMPType.WORK_FROM_HOME)
                .salary(25000)
                .mobileNumber("9999999999").build();

        //Put in a list
        List<Employee> employeesList = new ArrayList<>();

        employeesList.add(employee1);
        employeesList.add(employee2);
        employeesList.add(employee3);

        //Save Employee in DB
        repository.save(employee1);
        repository.save(employee2);
        repository.save(employee3);


        //Get the List
        List<Employee> ReturnedemployeeList = repository.findAll();
        System.out.println("Returned Size  --------------->  "+ ReturnedemployeeList.size());
        System.out.println("List Size  --------------->  "+ employeesList.size());
        //Then
        assertEquals(employeesList.size(), ReturnedemployeeList.size());

    }



    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }




}