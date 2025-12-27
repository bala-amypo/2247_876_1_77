
package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;

public interface UserService {

    User register(User user);

    User getById(Long id);

    User findByEmail(String email);

    List<User> listInstructors();

    //extra code
    
    User register(RegisterRequest request);

    User login(LoginRequest request);

    User getById(Long id);
}
