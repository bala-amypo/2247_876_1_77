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

    // REQUIRED BY TESTS
    private String code;

    private String skillName;
    private String category;
    private String description;
    private int minCompetencyScore;
    private Boolean active;
}
