package com.example.MuhanParking.api.user.service;

import com.example.MuhanParking.api.user.dto.request.*;
import com.example.MuhanParking.api.user.dto.response.FindUsernameResponse;
import com.example.MuhanParking.api.user.dto.response.LoginResponse;
import com.example.MuhanParking.api.user.dto.response.UserResponse;
import com.example.MuhanParking.exception.CustomException;
import com.example.MuhanParking.exception.ErrorCode;
import com.example.MuhanParking.model.User;
import com.example.MuhanParking.repository.UserRepository;
import com.example.MuhanParking.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor  // final 필드에 대한 생성자 자동 생성
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;  // repository 주입
    private final PasswordEncoder passwordEncoder;  // security config에서 빈으로 등록한거 주입
    private final JwtTokenProvider jwtTokenProvider;  // 이것도 추가해야해!

    // 회원가입
    @Transactional
    public void signUp(SignUpRequest request) {
        // 아이디 중복 체크
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        // 학번 중복 체크
        if (userRepository.existsByStudentId(request.getStudentId())) {
            throw new CustomException(ErrorCode.STUDENT_ID_ALREADY_EXISTS);
        }

        // 비밀번호 암호화 & 저장
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .gender(request.getGender())
                .studentId(request.getStudentId())
                .department(request.getDepartment())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .build();

        userRepository.save(user);
    }

    // 로그인
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 마지막 로그인 시간 업데이트
        user.updateLastLogin();
        userRepository.save(user);  // @Transactional 없어서 직접 저장

        // 토큰 생성
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        return new LoginResponse(token, user.getUsername());
    }

    // 내 정보 조회
    public UserResponse getMyInfo(String username) {  // 토큰에서 추출한 username 사용
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }

    // 정보 수정
    @Transactional
    public void updateUser(String username, UserUpdateRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 선택적 업데이트 (null이 아닌 필드만)
        if (request.getPhone() != null) {
            user.updatePhone(request.getPhone());
        }
        if (request.getEmail() != null) {
            user.updateEmail(request.getEmail());
        }
        if (request.getDepartment() != null) {
            user.updateDepartment(request.getDepartment());
        }
        if (request.getPassword() != null) {
            user.updatePassword(passwordEncoder.encode(request.getPassword()));
        }
        // @Transactional 있어서 자동 저장됨
    }
    @Transactional
    public void updateNotificationSetting(String username, boolean enabled) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.updateNotificationEnabled(enabled);
    }

    public FindUsernameResponse findUsername(FindUsernameRequest request) {
        User user = userRepository.findByNameAndStudentIdAndBirthDate(
                        request.getName(),
                        request.getStudentId(),
                        request.getBirthDate())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new FindUsernameResponse(user.getUsername(), maskUsername(user.getUsername()));
    }
    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByUsernameAndNameAndStudentId(
                        request.getUsername(),
                        request.getName(),
                        request.getStudentId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }

    private String maskUsername(String username) {
        if (username.length() <= 3) return username;
        int maskLength = username.length() - 2;
        return username.substring(0, 1) +
                "*".repeat(maskLength) +
                username.substring(username.length() - 1);
    }

}