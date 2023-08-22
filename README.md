# Speaking Clock

The Speaking Clock project is a simple application that converts the current time or entered time into words. It allows users to input the time in a 24-hour clock format and receive the corresponding time in words.

## Technologies Used

- Java 11
- Spring Boot
- Maven
- Swagger

## Prerequisites

- Java 11 or higher installed on your machine
- Maven

## Getting Started

Follow the steps below to get started with the Speaking Clock project:



1. Import the project into STS or any IDE as a Maven Project:
 

2. Build the project using Maven:

	mvn clean install

3. Run the application:

	mvn spring-boot:run

4. Access the application:

   Open a web browser and go to [http://localhost:8080](http://localhost:8080)

## API Endpoints

The Speaking Clock project provides the following API endpoints:

Method-GET
----------
- `/convert-time`: Converts the given time in a 24-hour clock format to words.
  - Request Parameter:
    - `time`: The time to convert (e.g., "07:55").
  - Example: `GET /convert-time?time=07:55`
  
  - `/current-time`: Displays the given Current time in a 24-hour clock format.
  
  - Example: `GET /current-time`

## Documentation

The project uses Swagger for API documentation. You can access the Swagger documentation by opening the following URL in your web browser: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)





