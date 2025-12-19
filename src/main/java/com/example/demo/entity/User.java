package com.example.skillgap.entity;

import com.example.skillgap.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // Stored hashed (BCrypt)

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
