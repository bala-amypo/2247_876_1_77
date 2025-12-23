package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ REQUIRED BY TEST CASES
    private String code;

    private String skillName;
    private String category;
    private String description;
    private int minCompetencyScore;

    // ✅ BOOLEAN → Boolean + DEFAULT VALUE
    @Builder.Default
    private Boolean active = true;
}
