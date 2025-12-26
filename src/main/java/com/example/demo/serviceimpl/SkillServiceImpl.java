// package com.example.demo.serviceimpl;

// import com.example.demo.entity.Skill;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.service.SkillService;

// import java.util.List;

// public class SkillServiceImpl implements SkillService {

//     private final SkillRepository repository;

//     public SkillServiceImpl(SkillRepository repository) {
//         this.repository = repository;
//     }

//     @Override
//     public Skill createSkill(Skill skill) {
//         if (repository.findByCode(skill.getCode()).isPresent()) {
//             throw new IllegalArgumentException("Skill code must be unique");
//         }
//         return repository.save(skill);
//     }

//     @Override
//     public Skill updateSkill(Long id, Skill skill) {
//         Skill existing = repository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Skill not found"));

//         existing.setName(skill.getName());
//         existing.setCategory(skill.getCategory());
//         existing.setDescription(skill.getDescription());
//         existing.setMinCompetencyScore(skill.getMinCompetencyScore());

//         return repository.save(existing);
//     }

//     @Override
//     public Skill getById(Long id) {
//         return repository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Skill not found"));
//     }

//     @Override
//     public List<Skill> getActiveSkills() {
//         return repository.findByActiveTrue();
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    // Constructor injection (used in tests)
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    /**
     * Create a new skill
     * Tests: t005, t006, t019
     */
    @Override
    public Skill createSkill(Skill skill) {

        // ❌ Duplicate skill code
        if (skillRepository.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("Skill code must be unique");
        }

        return skillRepository.save(skill);
    }

    /**
     * Update an existing skill
     * Test: t033
     */
    @Override
    public Skill updateSkill(Long id, Skill skill) {

        Skill existing = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        // Update only mutable fields
        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        existing.setDescription(skill.getDescription());
        existing.setMinCompetencyScore(skill.getMinCompetencyScore());
        existing.setActive(skill.isActive());

        return skillRepository.save(existing);
    }

    /**
     * Get skill by ID
     * Test: t048
     */
    @Override
    public Skill getById(Long id) {

        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    /**
     * Get all active skills
     * Test: t034
     */
    @Override
    public List<Skill> getActiveSkills() {
        return skillRepository.findByActiveTrue();
    }
}
