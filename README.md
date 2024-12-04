# Migros Online Case Application

This application tracks couriers and calculates their total travel distance using provided geolocation data. The service is built with Spring Boot and uses an H2 database for storage.

---

## Features
- Track couriers' geolocations.
- Calculate the total travel distance for each courier.
- RESTful API endpoints for interacting with the system.
- In-memory database (H2) for lightweight data storage.

---

## Prerequisites
Before running the application, ensure you have the following installed:
- Java 17 or higher
- Maven 3.6.0 or higher (for building and running)
---

## Setup and Running the Application

### Step 1: Clone the Repository
```bash
git clone https://github.com/atungacevik/migrosOnlineCase.git
cd migrosOnlineCase


Step 2: Build the Application

Use Maven to build the application:

mvn clean package

This will compile the code, run all tests, and generate a JAR file in the target/ directory.
Step 3: Run the Application

To start the application, run the following command:

java -jar target/migros-online-case-1.0.jar

Step 4: Access the Application

    Base URL: http://localhost:8080
    Example Endpoints:
        POST /courier/stream: Streams courier data.
        GET /courier/{id}/distance: Retrieves the total travel distance for a courier.

Step 5: Access the Database
       http://localhost:8080/h2-console/
       username = sa
       password = password


Running Tests
Step 1: Run Tests with Maven

To run the tests, execute:

mvn test

Step 2: Run Tests in the Packaged JAR

If you want to run tests from the packaged JAR:

    Ensure you have built the application:

mvn clean package

Use the JUnit Console Launcher:

java -cp target/migros-online-case-1.0.jar org.junit.platform.console.ConsoleLauncher --scan-classpath


README.md

# Migros Online Case Application

This application tracks couriers and calculates their total travel distance using provided geolocation data. The service is built with Spring Boot and uses an H2 database for storage.

---

## Features
- Track couriers' geolocations.
- Calculate the total travel distance for each courier.
- RESTful API endpoints for interacting with the system.
- In-memory database (H2) for lightweight data storage.

---

## Prerequisites
Before running the application, ensure you have the following installed:
- Java 17 or higher
- Maven 3.6.0 or higher (for building and running)
- Optional: Docker (for containerized deployment)

---

## Setup and Running the Application

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-repo/migros-online-case.git
cd migros-online-case

Step 2: Build the Application

Use Maven to build the application:

mvn clean package

This will compile the code, run all tests, and generate a JAR file in the target/ directory.
Step 3: Run the Application

To start the application, run the following command:

java -jar target/migros-online-case-1.0.jar

Step 4: Access the Application

    Base URL: http://localhost:8080
    Example Endpoints:
        POST /courier/stream: Streams courier data.
        GET /courier/{id}/distance: Retrieves the total travel distance for a courier.

Running Tests
Step 1: Run Tests with Maven

To run the tests, execute:

mvn test

Step 2: Run Tests in the Packaged JAR

If you want to run tests from the packaged JAR:

    Ensure you have built the application:

mvn clean package

Use the JUnit Console Launcher:

    java -cp target/migros-online-case-1.0.jar org.junit.platform.console.ConsoleLauncher --scan-classpath

Configuration
Application Properties

The default configuration uses an in-memory H2 database and runs on port 8080.

You can customize these settings in src/main/resources/application.properties:

server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

To access the H2 database console, visit http://localhost:8080/h2-console and use the credentials from the properties file.
Example API Usage
1. Stream Courier Location

Endpoint: POST /courier/stream

Request Body:

{
  "courierId": "C001",
  "lat": 40.9923307,
  "lng": 29.1244229
}

Response:

{
  "message": "Location added successfully"
}

2. Get Total Distance

Endpoint: GET /courier/C001/distance

Response:

{
  "courierId": "C001",
  "totalDistance": 860.5
}

Troubleshooting
Common Issues

    Port Already in Use: Change the port in application.properties or free the port.
    H2 Console Not Accessible: Ensure spring.h2.console.enabled=true.

Debugging Tests

Run tests with debugging enabled:

mvn test -Ddebug


    
