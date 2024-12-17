package com.example.MuhanParking.api.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotNull(message = "학번을 입력해주세요")
    private Integer studentId;

    @NotBlank(message = "새 비밀번호를 입력해주세요")
    @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다")
    private String newPassword;
}
