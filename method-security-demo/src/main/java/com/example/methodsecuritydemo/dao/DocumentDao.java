package com.example.methodsecuritydemo.dao;

import com.example.methodsecuritydemo.ds.Document;
import org.springframework.stereotype.Repository;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

@Repository
public class DocumentDao {

    private Map<String, Document> documents = Map.of(
            "abc123", new Document("natalie"),
            "wqe123", new Document("natalie"),
            "asd555", new Document("emma")
    );

    public Document findDocument(String code) {
        return documents.get(code);
    }
}
