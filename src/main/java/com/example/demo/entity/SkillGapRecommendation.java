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
// public class SkillGapRecommendation {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private StudentProfile student;

//     @ManyToOne
//     private Skill skill;

//     private Double gapScore;

//     private String generatedBy;

//     @Builder.Default
//     private Instant generatedAt = Instant.now();
//}
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile student;

    @ManyToOne
    private Skill skill;

    private Double gapScore;
    private String generatedBy;

    @Builder.Default
    private Instant generatedAt = Instant.now();
}
