// package com.example.demo.service;

// import com.example.demo.entity.User;
// import java.util.List;

// public interface UserService {

//     User register(User user);

//     User findByEmail(String email);

//     User getById(Long id);

//     List<User> listInstructors();
// }
package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import java.util.List;

public interface StudentProfileService {
    StudentProfile createOrUpdateProfile(StudentProfile profile);
    StudentProfile getByUserId(Long userId);
    StudentProfile getProfileById(Long id);
    List<StudentProfile> getAllProfiles();
}
