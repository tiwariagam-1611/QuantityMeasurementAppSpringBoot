QuantityMeasurementSpringBoot

The Quantity Measurement Application is a Spring Boot based REST API developed to manage operations on different physical quantities such as Length, Volume, Weight, and Temperature. The application allows users to perform arithmetic calculations, convert units, compare measurements, and maintain a history of all operations using a relational database.

This project demonstrates concepts such as layered architecture, REST API development, validation, exception handling, database integration, and API documentation using Swagger/OpenAPI.

Features

The application provides support for multiple quantity operations including:

* Addition
* Subtraction
* Division
* Comparison of quantities

The system also allows unit conversion between different measurement units.

Supported measurement categories include:

* Length
* Volume
* Weight
* Temperature

The application stores all operation records in the database and allows users to retrieve operation history whenever required.

History records can be filtered based on:

* Operation type
* Measurement category
* Error records

The application also provides functionality to count successful operations.

Additional features include:

* Input validation using Jakarta Validation
* Centralized exception handling
* Interactive API documentation using Swagger UI

Technology Stack

The project is developed using the following technologies:

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* H2 / MySQL Database
* Jakarta Validation
* OpenAPI (springdoc)
* Maven

Project Structure

The project follows a layered architecture consisting of:

* Config package for Swagger/OpenAPI configuration
* Controller package for REST API endpoints
* Service layer for business logic implementation
* Repository layer for database interaction
* Model package for DTOs and entities
* Unit package for measurement units and quantity logic
* Exception package for custom exceptions and global exception handling
* Test package for controller, service, and repository testing

API Endpoints

Base URL:
/api/v1/quantities

The application provides the following endpoints:

1. Compare Quantities
   Endpoint:
   POST /compare

Used to compare two quantities belonging to the same measurement type.

2. Convert Quantity
   Endpoint:
   POST /convert

Used to convert a quantity from one unit to another.

3. Add Quantities
   Endpoint:
   POST /add

Used to perform addition between two quantities.

4. Add Quantities with Target Unit
   Endpoint:
   POST /add-with-target-unit

Used to add quantities and return the result in a specified target unit.

5. Subtract Quantities
   Endpoint:
   POST /subtract

Used to subtract one quantity from another.

6. Subtract Quantities with Target Unit
   Endpoint:
   POST /subtract-with-target-unit

Used to subtract quantities and return the result in a target unit.

7. Divide Quantities
   Endpoint:
   POST /divide

Used to divide one quantity by another.

8. Get Operation History
   Endpoint:
   GET /history/operation/{operation}

Used to retrieve operation history based on operation type.

9. Get History by Measurement Type
   Endpoint:
   GET /history/type/{type}

Used to retrieve history records based on measurement category.

10. Get Operation Count
    Endpoint:
    GET /count/{operation}

Used to fetch the total count of successful operations.

11. Get Error History
    Endpoint:
    GET /history/errored

Used to retrieve all errored operation records.

Validation

The application uses Jakarta Validation to ensure data correctness and reliability.

Validation checks include:

* Required fields should not be null
* Measurement types should be valid
* Units should belong to the correct measurement category

If invalid input is provided, the application returns structured validation error responses.

Exception Handling

The application implements centralized exception handling using a global exception handler.

The exception handling mechanism manages:

* Validation exceptions
* Business logic exceptions
* Runtime exceptions

Appropriate HTTP status codes and descriptive error messages are returned for all errors.

Swagger Documentation

Swagger UI is integrated for API documentation and testing.

Swagger provides:

* Interactive API testing
* Request and response schemas
* Example payloads
* Endpoint descriptions

Swagger URL:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

How to Run the Application

Step 1:
Clone the repository into your local system.

Step 2:
Navigate to the project directory.

Step 3:
Build the project using:
mvn clean install

Step 4:
Run the application using:
mvn spring-boot:run

Future Enhancements

Possible future improvements for the project include:

* Adding multiplication functionality
* Improving validation logic
* Implementing authentication and authorization
* Deploying the application to cloud platforms such as AWS, Azure, or GCP
* Developing a frontend using React or Next.js
* Supporting additional measurement systems

Conclusion

The Quantity Measurement Application demonstrates a structured implementation of a Spring Boot REST API for handling quantity operations and unit conversions. The project follows best practices in layered architecture, validation, exception handling, database integration, and API documentation. It serves as a strong academic and learning project while also providing a scalable foundation for future enhancements.
