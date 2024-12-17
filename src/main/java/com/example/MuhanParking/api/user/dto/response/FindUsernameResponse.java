package com.example.MuhanParking.api.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindUsernameResponse {
    private String username;        // 실제 아이디
    private String maskedUsername;  // 마스킹된 아이디
}