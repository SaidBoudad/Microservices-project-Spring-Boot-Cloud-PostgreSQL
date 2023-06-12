# Microservices-project-Spring-Boot-Cloud-PostgreSQL
Microservices project using Spring Boot 3, Spring Cloud, and PostgreSQL database.  concepts such as API Gateway, Config Server, Discovery Server, and hands-on implementation of two real-world microservices, Student and School.
 
the comunication between srevices is implemented by 3 way: just for study purpose
1-RestTemplate: RestTemplate is a synchronous HTTP client
2-WebClient: WebClient is a non-blocking, reactive HTTP client introduced in Spring WebFlux. It provides a more modern and flexible approach to making HTTP requests. WebClient is suitable for building reactive and asynchronous applications.
3-OpenFeign: OpenFeign is a declarative web service client developed by Netflix and integrated into Spring Cloud. It simplifies the creation of RESTful service clients by using annotations to define the desired HTTP requests and responses. OpenFeign is a convenient choice for service-to-service communication in a microservices architecture.

the Microservices project components:

Student Microservice
The Student Microservice is responsible for managing student-related data and operations, such as adding, updating, and retrieving student records.

School Microservice
The School Microservice manages school-related data and operations, including adding, updating, and retrieving school records.
Using OpenFeign - Inter-Service Communication

Discovery Server
The Discovery Server provides service registration and discovery, enabling seamless service-to-service communication within the microservices ecosystem.

API Gateway
The API Gateway serves as the single entry point for all client requests, managing and routing them to the appropriate microservices.

Config Server
The Config Server centralizes configuration management for all microservices, simplifying application maintenance and consistency across environments.


This project demonstrates inter-service communication using OpenFeign, a declarative REST client that simplifies service-to-service communication within the microservices ecosystem.
We use the OpenFeign library to make a REST API call from school service to student service.
In order to use the OpenFeign library,we add the below dependency to the pom.xml file of the school service project : 
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
