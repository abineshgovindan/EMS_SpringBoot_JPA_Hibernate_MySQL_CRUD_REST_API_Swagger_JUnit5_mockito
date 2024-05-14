package com.SpringBootHibernateMySQLCRUDRESTAPI.App.DTO;

import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.EMPType;
import com.SpringBootHibernateMySQLCRUDRESTAPI.App.Model.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeRequest {
    private long ID;
    @NotBlank(message = "Enter the valid First Name.")
    private String firstName;
    @NotBlank(message = "Enter the valid Last Name.")
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    @Min(value = 21, message = "Employee must be equal to 21 or greater")
    @Max(value = 60, message = "Employee must be equal to 60 or Lesser")
    private int age;

    //@NotBlank(message = "Enter the valid EMP Type.")
    private Gender gender;
    //@NotBlank(message = "Enter the valid EMP Type.")
    private EMPType empType;
    @Min(15000)
    @Max(550000)
    private int salary;

    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String mobileNumber;
}
