package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentProfileId;
    private Long skillId;

    private double currentScore;
    private double targetScore;
    private double gapScore;
    private String calculatedAt;
}
