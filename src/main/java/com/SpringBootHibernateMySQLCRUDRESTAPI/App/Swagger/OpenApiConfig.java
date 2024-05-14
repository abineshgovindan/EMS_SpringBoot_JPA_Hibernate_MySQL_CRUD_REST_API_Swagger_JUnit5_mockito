package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

public class OpenApiConfig {

    @Bean
    public OpenAPI awesomeAPI(){
        return new  OpenAPI().info(new Info().title("Employee Management System REST API.")
                .version("1.1.0") 
                .description("This Java Spring Boot project utilizes Hibernate and JPA to implement a CRUD (Create, Read, Update, Delete) REST API for an Employee Management System. The application is built on top of MySQL as the database, allowing seamless management of employee data.")
                );


    }
}
