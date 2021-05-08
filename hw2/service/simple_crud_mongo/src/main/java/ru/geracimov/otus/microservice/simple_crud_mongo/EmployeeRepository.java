package ru.geracimov.otus.microservice.simple_crud_mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

}