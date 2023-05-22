package com.example.apisecurity.service;

import com.example.apisecurity.data.Token;
import com.example.apisecurity.data.User;
import com.example.apisecurity.data.UserDao;
import com.example.apisecurity.exception.PasswordNotMatchError;
import com.example.apisecurity.exception.UserNotFound;
import com.example.apisecurity.exception.WrongPassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Value("${secret.access-token.key}")
    private String accessSecret;
    @Value("${secret.refresh-token.key}")
    private String refreshSecret;

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String firstName, String lastName, String email, String password, String confirmPassword) {

        if (!password.equals(confirmPassword)){
            throw new PasswordNotMatchError();
        }

        try {
            return userDao.save(User.of(firstName, lastName, email, passwordEncoder.encode(password)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already used");
        }
    }

    public Login login(String email, String password) {
        var user = userDao.findUserByEmail(email).orElseThrow(UserNotFound::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPassword();
        }

        var login = Login.of(user.getId(), accessSecret, refreshSecret);
        var token = new Token(
                login.getRefreshToken().getToken(),
                login.getRefreshToken().getIssuedAt(),
                login.getRefreshToken().getExpiredAt()
        );
        user.addToken(token);
        userDao.save(user);

        return login;

    }
}
