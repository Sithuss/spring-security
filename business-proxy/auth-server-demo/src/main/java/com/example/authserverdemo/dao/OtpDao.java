package com.example.authserverdemo.dao;

import com.example.authserverdemo.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OtpDao extends JpaRepository<Otp, String> {

    Optional<Otp> findOtpByUsername(String username);
}
