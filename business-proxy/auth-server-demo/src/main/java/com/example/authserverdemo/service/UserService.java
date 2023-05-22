package com.example.authserverdemo.service;

import com.example.authserverdemo.dao.OtpDao;
import com.example.authserverdemo.dao.UserDao;
import com.example.authserverdemo.entity.Otp;
import com.example.authserverdemo.entity.User;
import com.example.authserverdemo.util.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OtpDao otpDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public void auth(User user) {
        Optional<User> o = userDao.findUserByUsername(user.getUsername());
        if (o.isPresent()) {
            User u = o.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())){
                renewOtp(u);
            }
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private void renewOtp(User u) {

        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> userOtp = otpDao.findOtpByUsername(u.getUsername());

        if (userOtp.isPresent()) {
            Otp otp=userOtp.get();
            otp.setCode(code);
        }
        else {
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpDao.save(otp);
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpDao.findOtpByUsername(otpToValidate.getUsername());

        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otp.getCode().equals(otpToValidate.getCode())) {
                return true;
            }
        }
        return false;
    }



}
