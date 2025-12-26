// // package com.example.demo.service;

// // import com.example.demo.entity.Skill;
// // import java.util.List;

// // public interface SkillService {

// //     Skill createSkill(Skill skill);

// //     Skill updateSkill(Long id, Skill skill);

// //     Skill getById(Long id);

// //     List<Skill> getActiveSkills();
// // }
// package com.example.demo.service;

// import com.example.demo.entity.Skill;

// import java.util.List;

// public interface SkillService {

//     Skill createSkill(Skill skill);

//     Skill updateSkill(Long id, Skill skill);

//     Skill getById(Long id);

//     List<Skill> getActiveSkills();
// }
package com.example.demo.service;
@Service
import com.example.demo.entity.Skill;
import java.util.List;

public interface SkillService {
    Skill createSkill(Skill s);
    Skill updateSkill(Long id, Skill s);
    Skill getById(Long id);
    List<Skill> getActiveSkills();
}
