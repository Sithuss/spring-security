package com.example.jpasigninsignup.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameNotAdminValidator implements ConstraintValidator<NameNotAdmin, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.equalsIgnoreCase("admin");
    }
}
