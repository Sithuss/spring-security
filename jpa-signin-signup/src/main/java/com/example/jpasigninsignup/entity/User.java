package com.example.jpasigninsignup.entity;

import com.example.jpasigninsignup.validation.NameNotAdmin;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NameNotAdmin(message = "{validator.msg.user}")
    private String username;
    private String password;
    @Transient
    private String repeatedPassword;
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles= new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void addRole(Role role) {
        role.getUser().add(this);
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
