// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/gaps")
// public class SkillGapController {
// }
package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<SkillGapRecommendation>> getRecommendations(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
                skillGapService.getRecommendations(studentId)
        );
    }
}
