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
// public class AssessmentResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String assessmentId;

//     @ManyToOne
//     @JoinColumn(name = "student_profile_id")
//     private StudentProfile studentProfile;

//     @ManyToOne
//     @JoinColumn(name = "skill_id")
//     private Skill skill;

//     private Double score;

//     @Builder.Default
//     private Double maxScore = 100.0;

//     @Builder.Default
//     private Instant attemptedAt = Instant.now();
// }
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assessmentId;

    private Double score;

    @Builder.Default
    private Double maxScore = 100.0;

    @Builder.Default
    private Instant attemptedAt = Instant.now();

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;
}
