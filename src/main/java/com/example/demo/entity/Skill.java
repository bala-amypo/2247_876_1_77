package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REQUIRED BY TEST
    private String code;

    // TEST USES name(), NOT skillName()
    private String name;

    private String category;
    private String description;
    private int minCompetencyScore;

    @Builder.Default
    private boolean active = true;
}
