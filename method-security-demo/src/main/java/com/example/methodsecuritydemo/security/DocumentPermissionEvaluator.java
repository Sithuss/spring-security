package com.example.methodsecuritydemo.security;


import com.example.methodsecuritydemo.dao.DocumentDao;
import com.example.methodsecuritydemo.ds.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String p = (String) permission;
        boolean admin=authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));

        return admin || document.getOwner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {

        String code = targetId.toString();
        Document document = documentDao.findDocument(code);
        String p = (String) permission;
        boolean admin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));
        return admin || document.getOwner().equals(authentication.getName());
    }

    @Autowired
    private DocumentDao documentDao;
}
