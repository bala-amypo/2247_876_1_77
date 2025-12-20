package com.example.demo.dto;

import com.example.demo.entity.Role;
import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

    // getters and setters
}
