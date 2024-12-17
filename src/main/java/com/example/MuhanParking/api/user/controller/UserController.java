package com.example.MuhanParking.api.user.controller;

import com.example.MuhanParking.api.user.dto.request.*;
import com.example.MuhanParking.api.user.dto.response.FindUsernameResponse;
import com.example.MuhanParking.api.user.dto.response.LoginResponse;
import com.example.MuhanParking.api.user.dto.response.UserResponse;
import com.example.MuhanParking.api.user.service.UserService;
import com.example.MuhanParking.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import java.io.IOException;
import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/auth/signup")
    public ResponseDto<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        try {
            userService.signUp(request);
            return ResponseDto.success("회원가입이 완료되었습니다", null);
        } catch (Exception e) {
            log.error("회원가입 실패: ", e);  // 에러 로그 추가
            return ResponseDto.fail(e.getMessage());
        }
    }
    // 로그인
    @PostMapping("/auth/login")
    public ResponseDto<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseDto.success("로그인이 완료되었습니다", response);
    }

    // 로그아웃 - 클라이언트에서 토큰 삭제하면 되니까 간단하게
    @PostMapping("/auth/logout")
    public ResponseDto<Void> logout() {
        return ResponseDto.success("로그아웃 되었습니다", null);
    }

    // 마이페이지- 내 정보 조회
    @GetMapping("/profile")
    public ResponseDto<UserResponse> getMyInfo(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponse response = userService.getMyInfo(userDetails.getUsername());
        return ResponseDto.success("내 정보 조회 성공", response);
    }
   //정보수정 - 정보조회
    @GetMapping("/profile/edit")
    public ResponseDto<UserResponse> getProfileForEdit(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponse response = userService.getMyInfo(userDetails.getUsername());
        return ResponseDto.success("프로필 수정 정보 조회 성공", response);
    }

    // 정보 수정
    @PutMapping("/profile")
    public ResponseDto<Void> updateUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserUpdateRequest request) {
        userService.updateUser(userDetails.getUsername(), request);
        return ResponseDto.success("회원정보가 수정되었습니다", null);
    }


    @PostMapping("/auth/find-username")
    public ResponseDto<FindUsernameResponse> findUsername(@Valid @RequestBody FindUsernameRequest request) {
        FindUsernameResponse response = userService.findUsername(request);
        return ResponseDto.success("아이디 찾기 성공", response);
    }

    @PostMapping("/auth/reset-password")
    public ResponseDto<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ResponseDto.success("비밀번호가 변경되었습니다", null);
    }
}