package com.example.MuhanParking.api.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다")
    private String password;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요")
    private String phone;

    @NotBlank(message = "주소를 입력해주세요")
    private String address;

    @NotBlank(message = "성별을 입력해주세요")
    private String gender;

    @NotNull(message = "학번을 입력해주세요")
    private Integer studentId;

    @NotNull(message = "학과를 입력해주세요")
    private String department;

    @NotBlank(message = "생년월일을 입력해주세요")
    private String birthDate;

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
}
