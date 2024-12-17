package com.example.MuhanParking.api.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FindUsernameRequest {
    private String name;
    private Integer  studentId;
    private String birthDate;
}