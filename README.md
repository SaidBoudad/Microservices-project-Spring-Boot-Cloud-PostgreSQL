# Microservices-project-Spring-Boot-Cloud-PostgreSQL
Microservices project using Spring Boot 3, Spring Cloud, and PostgreSQL database.  concepts such as API Gateway, Config Server, Discovery Server, and hands-on implementation of two real-world microservices, Student and School.

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
