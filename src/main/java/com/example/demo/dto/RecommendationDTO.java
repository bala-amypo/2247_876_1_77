// package com.example.demo.dto;

// public class RecommendationDTO {

//     private Long studentProfileId;
//     private Long skillId;

//     public RecommendationDTO() {
//     }

//     public RecommendationDTO(Long studentProfileId, Long skillId) {
//         this.studentProfileId = studentProfileId;
//         this.skillId = skillId;
//     }

//     public Long getStudentProfileId() {
//         return studentProfileId;
//     }

//     public void setStudentProfileId(Long studentProfileId) {
//         this.studentProfileId = studentProfileId;
//     }

//     public Long getSkillId() {
//         return skillId;
//     }

//     public void setSkillId(Long skillId) {
//         this.skillId = skillId;
//     }
// }
package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationDTO {
    private Long studentProfileId;
    private Long skillId;
    private Double gapScore;
    private String priority;
    private Instant generatedAt;
}
