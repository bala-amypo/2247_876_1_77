// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class User {

//     public enum Role { STUDENT, INSTRUCTOR, ADMIN }

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(unique = true)
//     private String email;

//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role;
//}
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // ✅ FIX: avoid reserved keyword
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String fullName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        INSTRUCTOR,
        STUDENT
    }

    // getters + setters
}
