package com.example.methodsecurity.service;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private Map<String, List<String>> secretName=Map.of(
            "john", List.of("Far from the Maddening Crowd",
                    "Under the Greenwood Tree",
                    "Tess of d'Urbervilles"),
            "emma", List.of("The Great Exception",
                    "A Tale of Two Cities",
                    "Oliver Twist")
    );
    @PreAuthorize("hasAnyAuthority('write', 'read')")
    public String getName() {
        return "Oh!Danny Boy!";
    }

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretName(String name) {
        return secretName.get(name);
    }
}
