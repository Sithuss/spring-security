package com.example.apisecurity.controller;

import com.example.apisecurity.data.User;
import com.example.apisecurity.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello@";
    }

    record RequestUser (
            @JsonProperty("first_name")String firstName,
            @JsonProperty("last_name")String lastName,
            String email, String password, @JsonProperty("confirm_password")String confirmPassword) {}

    record RegisterResponse(
            @JsonProperty("first_name")String firstName,
            @JsonProperty("last_name")String lastName,
            String email){}

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RequestUser requestUser) {
        User user = userService.register(
            requestUser.firstName(),
                requestUser.lastName(),
                requestUser.email(),
                requestUser.password(),
                requestUser.confirmPassword()
        );
        return new  RegisterResponse(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    record LoginResponse(String jwt) {}

    record LoginRequest(String email, String password) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        var login = userService.login(loginRequest.email(), loginRequest.password());

        var cookie = new Cookie("refresh_token",login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");
        response.addCookie(cookie);

        return new LoginResponse(login.getAccessToken().getToken());
    }

    record UserResponse(
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name")String lastName,
            String email
    ){}

    @GetMapping("/user")
    public UserResponse user() {
        return null;
    }
}
