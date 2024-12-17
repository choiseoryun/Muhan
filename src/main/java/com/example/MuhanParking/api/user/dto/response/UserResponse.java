package com.example.MuhanParking.api.user.dto.response;

import com.example.MuhanParking.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String name;
    private String phone;
    private String address;
    private String gender;
    private Integer studentId;
    private String department;
    private String birthDate;
    private String email;


    private boolean notificationEnabled;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getGender(),
                user.getStudentId(),
                user.getDepartment(),
                user.getBirthDate(),
                user.getEmail(),
                user.isNotificationEnabled()
        );
    }
}