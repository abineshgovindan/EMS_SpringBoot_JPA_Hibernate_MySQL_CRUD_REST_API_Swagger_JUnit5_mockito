# Employee Management System REST API

This Java Spring Boot project utilizes Hibernate and JPA to implement a CRUD (Create, Read, Update, Delete) REST API for an Employee Management System. The application is built on top of MySQL as the database, allowing seamless management of employee data.

## Employee REST Controller

### Get employee based on the id.

Get employee based on the id from the DB.

- **HTTP Method:** GET
- **URL:** `/api/employees/{id}`
- **Parameters:**
    - `id` (path, integer): Unique identifier of the employee.
- **Responses:**
    - 200: HTTP Status OK
        - Content:
            - Schema: [Employee](#components-schemas-Employee)
    - 500: Internal Server Error
        - Content:
            - Schema:
                - type: object
                - additionalProperties:
                    - type: string

### Update employee data based on the id.

Update employee data based on the id in the DB.

- **HTTP Method:** PUT
- **URL:** `/api/employees/{id}`
- **Parameters:**
    - `id` (path, integer): Unique identifier of the employee.
- **Request Body:**
    - Content:
        - application/json:
            - Schema: [EmployeeRequest](#components-schemas-EmployeeRequest)
    - Required: true
- **Responses:**
    - 201: HTTP Status Created
        - Content:
            - Schema: [Employee](#components-schemas-Employee)
    - 500: Internal Server Error
        - Content:
            - Schema:
                - type: object
                - additionalProperties:
                    - type: string

### Delete employee based on the id.

Delete employee based on the id in the DB.

- **HTTP Method:** DELETE
- **URL:** `/api/employees/{id}`
- **Parameters:**
    - `id` (path, integer): Unique identifier of the employee.
- **Responses:**
    - 200: HTTP Status OK
        - Content:
            - Schema:
                - type: string
    - 500: Internal Server Error
        - Content:
            - Schema:
                - type: object
                - additionalProperties:
                    - type: string

### Get all employees as a list.

Get all employees as a list from DB.

- **HTTP Method:** GET
- **URL:** `/api/employees`
- **Responses:**
    - 200: HTTP Status OK
        - Content:
            - Schema:
                - type: array
                - items:
                    - $ref: "#/components/schemas/Employee"
    - 500: Internal Server Error
        - Content:
            - Schema:
                - type: object
                - additionalProperties:
                    - type: string

### Insert new Employee.

- **HTTP Method:** POST
- **URL:** `/api/employees`
- **Request Body:**
    - Content:
        - application/json:
            - Schema: [EmployeeRequest](#components-schemas-EmployeeRequest)
    - Required: true
- **Responses:**
    - 201: HTTP Status Created
        - Content:
            - Schema: [Employee](#components-schemas-Employee)
    - 500: Internal Server Error
        - Content:
            - Schema:
                - type: object
                - additionalProperties:
                    - type: string

## Components

### Schemas

#### EmployeeRequest

- **Required Fields:**
    - firstName (string)
    - lastName (string)

- **Properties:**
    - firstName (string)
    - lastName (string)
    - email (string)
    - age (integer, format: int32, minimum: 21, maximum: 60)
    - gender (string, enum: MALE, FEMALE, OTHERS)
    - empType (string, enum: FULL_TIME, PART_TIME, WORK_FROM_HOME)
    - salary (integer, format: int32, minimum: 15000, maximum: 550000)
    - mobileNumber (string, pattern: "^\d{10}$")

#### Employee

- **Properties:**
    - id (integer, format: int64)
    - firstName (string)
    - lastName (string)
    - email (string)
    - age (integer, format: int32)
    - gender (string, enum: MALE, FEMALE, OTHERS)
    - empType (string, enum: FULL_TIME, PART_TIME, WORK_FROM_HOME)
    - salary (number, format: double)
    - mobileNumber (string)

_All details about the employee._
