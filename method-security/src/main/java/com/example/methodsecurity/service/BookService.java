package com.example.methodsecurity.service;

import com.example.methodsecurity.ds.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private Map<String, Employee> records =
            Map.of(
                    "emma", new Employee(
                            "Emma Thompson",
                            List.of("Karamazov Brothers"),
                            List.of("Programmer", "Reader")
                    ),
                    "nataile", new Employee("Natalie Portman",
                            List.of("Wuthering Heights"),
                            List.of("Accountant", "Reader"))
            );


    @PostAuthorize("returnObject.roles.contains('Reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
