# Desafio Itau

This project is a Spring Boot application that provides endpoints for creating transactions, clearing transactions, and
retrieving transaction statistics.

## Technologies Used

- Java
- Spring Boot
- Maven
- Docker
- JUnit
- Mockito

## Prerequisites

- Java 21
- Maven
- Docker

## Running the Application

### Using Maven

To run the application using Maven, use the following command:

`mvn spring-boot:run`

### Using Docker

`docker compose up -d`

## Endpoints

### Create Transaction

- URL: `/transacao`

- Method: **POST**

- Request Body:

```json
{
  "valor": 123.45,
  "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

- Responses:

    - 201 Created: Transaction created successfully.

    - 400 Bad Request: Invalid request.

    - 422 Unprocessable Entity: Validation error in input data.

### Clear Transactions

- URL: `/transacao`
- Method: **DELETE**
- Responses:

    - 204 No Content: Transactions cleared successfully.

### Get Transaction Statistics

- URL: `/estatistica`

- Method: **GET**

- Responses:

    - 200 OK: Statistics retrieved successfully.

## Running Tests

To run the tests, use the following command:

`mvn test`

## Swagger UI Documentation

To access the Swagger UI documentation, navigate to:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
