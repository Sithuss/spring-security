package com.example.apisecurity.Interceptor;

import com.example.apisecurity.exception.NoBearerError;
import com.example.apisecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public AuthorizationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String handlerStr = request.getHeader("Authorization");

        if (handlerStr == null || !handlerStr.startsWith("Bearer ")) {
            throw new NoBearerError();
        }
        request.setAttribute("user", handlerStr.substring(7));
        return true;
    }
}
