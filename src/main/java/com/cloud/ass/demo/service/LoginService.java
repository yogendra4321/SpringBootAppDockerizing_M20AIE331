package com.cloud.ass.demo.service;

import com.cloud.ass.demo.entity.Login;
import com.cloud.ass.demo.entity.request.UserDetails;
import com.cloud.ass.demo.exceptions.UserNotFoundException;
import com.cloud.ass.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public Login checkUsername(String username) {

        Optional<Login> loginDetails = loginRepository.findByUsername(username);
        if (!loginDetails.isPresent()) throw new UserNotFoundException(username);

        return loginDetails.get();
    }

    public Login addUser(String username, String name) {

        Login userLogin = new Login();
        userLogin.setUsername(username);
        userLogin.setName(name);
        return loginRepository.save(userLogin);

    }
}
