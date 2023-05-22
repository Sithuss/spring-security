package com.example.methodsecuritydemo.service;

import com.example.methodsecuritydemo.dao.DocumentDao;
import com.example.methodsecuritydemo.ds.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDao;

    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_ADMIN')")
    public Document getDocumentV2(String code) {
        return documentDao.findDocument(code);
    }

    @PostAuthorize("hasPermission(returnObject,'ROLE_ADMIN')")
    public Document getDocument(String code) {
        return documentDao.findDocument(code);
    }
}
