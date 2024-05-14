<h1>SpringBoot_JPA_Hibernate_MySQL_CRUD_REST_API</h1>

----
## Project Overview

<p>This Java Spring Boot project utilizes Hibernate and JPA to implement a CRUD 
(Create, Read, Update, Delete) REST API for an Employee Management System. 
The application is built on top of MySQL as the database, allowing seamless management of employee data.</p>

---
## Swagger OpenAPI Doc

<h6>Explore Swagger OpenAPI EMS Doc Spec.</h6>
[Swagger OpenAPI Doc](Swagger-doc.md) 

---
## Features

- **Add Employee:** Endpoint to add a new employee with details such as first name, last name, email, age, gender, employment type, salary, and mobile number.

- **Remove Employee:** Endpoint to remove an existing employee based on their unique identifier.

- **Update Employee:** Endpoint to update the details of an existing employee.

- **Select Employee:** Endpoint to retrieve details of a specific employee based on their unique identifier.
----
## Example Data

```json
{
  "firstName": "Jordan",
  "lastName": "Miller",
  "email": "jordan.miller@example.com",
  "age": 24,
  "gender": "OTHERS",
  "empType": "PART_TIME",
  "salary": 38000,
  "mobileNumber": "4321098765"
}
```
-----
### Create a database using below DDL script:
- Create a database

  ```sql
  CREATE DATABASE 'EMS';
  USE EMS;
  ```

- Create a Employee Table

  ```sql
  CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHERS') NOT NULL,
    emp_type ENUM('FULL_TIME', 'PART_TIME', 'WORK_FROM_HOME') NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL );
  ```

