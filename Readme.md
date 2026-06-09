PayFlow API

Project Overview:

PayFlow is a simplified fintech backend application built using Spring Boot. The application provides REST APIs for registering users, retrieving user information, searching users by UPI ID, and recording money transfers between users.
The project demonstrates a layered architecture using Controller, Service, Repository, and Entity layers along with Spring Data JPA and an H2 in-memory database.
There is no frontend application. All functionality is accessed through HTTP requests using tools such as curl or insomnia.


Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

Project Structure
src/main/java
controller
- UserController
- TransactionController
service
- UserService
- TransactionService
repository
- UserRepository
- TransactionRepository
entity
- User
- Transaction
What Each Layer Does
Entity Layer
The Entity layer defines the database tables and maps Java classes to database records using JPA annotations.
Repository Layer
The Repository layer communicates with the database. It uses Spring Data JPA and extends JpaRepository to perform CRUD operations.
Service Layer
The Service layer contains business logic and acts as an intermediary between controllers and repositories.
Controller Layer
The Controller layer exposes REST APIs and handles incoming HTTP requests and outgoing HTTP responses.

Spring Boot Features Used

1. Embedded Server
Spring Boot provides an embedded Tomcat server.
In this project, the application runs directly using:
mvn spring-boot:run
No external server installation is required.
2. Auto Configuration
Spring Boot automatically configures:
- Spring MVC
- H2 Database
- Hibernate
- Jackson JSON Processing
based on the dependencies included in the project.
3. Production Ready Defaults
Spring Boot provides sensible defaults such as:
- Embedded Tomcat
- JSON serialization
- Error handling
- Dependency management
This significantly reduces configuration effort.

- Database Configuration

The application uses an H2 in-memory database.

H2 Console
http://localhost:8080/h2-console
Entity Details
User Entity
Fields:

- userId
- name
- upiId
- balance
- phoneNumber

Transaction Entity

Fields:

- transactionId
- senderUpiId
- receiverUpiId
- amount
- notes

For this assignment, sender and receiver information are stored as plain strings and are not linked using foreign key relationships.



Repository Layer

UserRepository

Derived Query Method:

Optional<User> findByUpiId(String upiId);

Spring Data JPA automatically derives the SQL query from the method name.

Example generated SQL:

select *
from users
where upi_id = ?

Custom JPQL Query
Example:
@Query("select u from User u where u.balance > :balance")
This query returns all users whose balance is greater than the provided amount.

REST API Endpoints

User APIs
Register User
POST /users
Get All Users
GET /users
Get User By ID
GET /users/{id}
Get User By UPI ID
GET /users/upi/{upiId}
Delete User
DELETE /users/{id}


Transaction APIs
Create Transaction
POST /transactions
Get All Transactions
GET /transactions
Get Transaction y ID
GET /transactions/{id}



Conceptual Questions
1. Request Lifecycle
When a client sends a request such as POST /users, the request first reaches the DispatcherServlet. The DispatcherServlet consults the HandlerMapping to identify the appropriate controller method. The Handler Adapter then invokes the controller method. The controller delegates processing to the service layer, which may interact with the repository and database. Finally, the response is returned to the client.

2. Serialization
Spring Boot uses the Jackson library to convert JSON data into Java objects and Java objects back into JSON. When a JSON payload such as {"name":"Priya","upiId":"priya@okaxis"} is sent, Jackson maps the JSON fields to the corresponding Java object fields. If the JSON key is written as upi_id instead of upiId, the mapping may fail and the field may be populated as null because the names do not match.

3. Spring Boot Features
Embedded Server:
The application runs using the embedded Tomcat server without requiring a separate installation.
Auto Configuration:
Spring Boot automatically configures Spring MVC, Hibernate, H2 Database, and Jackson based on project dependencies.
Production Ready Defaults:
Spring Boot provides default configurations for logging, JSON processing, error handling, and dependency management.

4. Spring vs Spring Boot
If plain Spring Framework had been used, manual configuration of the servlet container, dependency management, datasource configuration, and application context setup would have been required. Spring Boot automatically performs these configurations, allowing developers to focus on application logic.
5. Stateless REST
A stateless REST API does not store information about previous requests. Every request contains all information necessary for processing. This allows requests to be handled by any server instance behind a load balancer and improves scalability and reliability.
6. Persistence
The application stores users and transactions in an H2 database. If a Java List had been used instead, all data would be lost whenever the application stopped or restarted because Lists exist only in memory. Databases provide persistent storage and retain information across application restarts.

Screenshots Included in the word document
The following screenshots are attached separately:
1. H2 Console showing the Users table.
2. H2 Console showing the Transaction table. 
3. curl command execution outputs.

---- changes for the PR -------------
