package com.example.MuhanParking.api.user.dto.response;

import com.example.MuhanParking.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponse {
    private String name;
    private String email;
    private Integer studentId;
    private String department;


    public UserProfileResponse(User user) {
        this.name = user.getName();
        this.studentId = user.getStudentId();
        this.department = user.getDepartment();
        this.email = user.getEmail();
    }
}