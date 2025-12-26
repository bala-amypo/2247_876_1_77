// package com.example.demo.service;

// import com.example.demo.entity.StudentProfile;

// public interface StudentProfileService {

//     StudentProfile createOrUpdateProfile(StudentProfile profile);

//     StudentProfile getByUserId(Long userId);
//}
package com.example.demo.service;

import com.example.demo.entity.StudentProfile;

public interface StudentProfileService {
    StudentProfile createOrUpdateProfile(StudentProfile p);
    StudentProfile getByUserId(Long userId);
}
