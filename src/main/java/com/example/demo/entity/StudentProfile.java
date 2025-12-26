// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.Instant;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class StudentProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String enrollmentId;
//     private String grade;

//     @Builder.Default
//     private Instant lastUpdatedAt = Instant.now();

//     @PreUpdate
//     public void preUpdate() {
//         this.lastUpdatedAt = Instant.now();
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(unique = true)
    private String enrollmentId;

    private String cohort;
    private Integer yearLevel;
    private Boolean active = true;

    private Instant lastUpdatedAt = Instant.now();

    @PreUpdate
    public void preUpdate() {
        lastUpdatedAt = Instant.now();
    }
}
