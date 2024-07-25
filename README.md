# Book Station

Book Station is a library management system built with Spring Boot.

## Prerequisites

- Java 17
- Maven
- Docker

## Getting Started

1. Clone the repository:

   ```
   git clone https://github.com/your-username/book-station.git
   cd book-station
   ```


2. Configure the database:
   Open `src/main/resources/application.properties` and update the database URL, username, and password if necessary.

   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/book_station
   spring.datasource.username=admin
   spring.datasource.password=admin
   ```


3. Start docker container:
   ```
   docker compose up --build -d
   ```


4. Build the project:

   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application should now be running at `http://localhost:8080`

## Database Management

### Drop and Recreate Database

To drop the existing database and create a new one:

```
mvn sql:execute@drop-db
```

This command will drop the `book_station` database if it exists and create a new one.

### Populate Database

To populate the database with initial data:

```
mvn spring-boot:run "-Dspring-boot.run.arguments=populate"
```

This command will run the application with the "populate" argument, triggering the data population process.

## API Documentation

Once the application is running, you can access the Swagger UI for API documentation at:

`http://localhost:8080/swagger-ui.html`

## Available Endpoints

- Books: `/api/book`
- Magazines: `/api/magazine`
- Authors: `/api/authors`
- Publishers: `/api/publishers`
- Genres: `/api/genres`
- Loans: `/api/loans`
- Reservations: `/api/reservations`
- Employees: `/api/employees`

