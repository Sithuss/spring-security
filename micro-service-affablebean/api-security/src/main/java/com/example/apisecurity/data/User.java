package com.example.apisecurity.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ToString
public class User {

    @Id
    @Getter
    private Long id;
    @JsonProperty("first_name")
    @Getter @Setter
    private String firstName;
    @JsonProperty("last_name")
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;

    @MappedCollection
    private Set<Token> tokens = new HashSet<>();

    public void addToken(Token token) {
        this.tokens.add(token);
    }

    public static User of(String firstName, String lastName, String email, String password) {
        return new User(null, firstName, lastName, email, password, Collections.emptySet());
    }

    public User() {
    }

    @PersistenceConstructor

    public User(Long id, String firstName, String lastName, String email, String password, Collection<Token> tokens) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.tokens.addAll(tokens);
    }
}
