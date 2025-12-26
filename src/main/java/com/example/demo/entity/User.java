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
import lombok.*;

@Entity
@Table(name = "users") // ✅ FIX: "user" is reserved keyword in H2
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    public enum Role {
        STUDENT,
        INSTRUCTOR,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING) // ✅ FIX: avoid ENUM DDL
    @Column(length = 20)
    private Role role;
}
