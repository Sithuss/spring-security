package com.example.jpasigninsignup.validation;

import com.example.jpasigninsignup.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        if (!user.getPassword().equals(user.getRepeatedPassword())){
            errors.rejectValue("password", null, "Password and repeated password must be match!");
        }
    }
}
