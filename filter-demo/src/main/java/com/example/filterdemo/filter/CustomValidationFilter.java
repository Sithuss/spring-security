package com.example.filterdemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.RequestFilter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class CustomValidationFilter implements Filter {

    // BEFORE USERNAME AND PASSWORD


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;
        String key=httpRequest.getHeader("x-api-key");

        if (key==null || key.isBlank()) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
