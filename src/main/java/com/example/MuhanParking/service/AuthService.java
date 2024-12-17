package com.example.MuhanParking.service;

import com.example.MuhanParking.api.user.dto.request.LoginRequest;
import com.example.MuhanParking.api.user.dto.request.SignUpRequest;
import com.example.MuhanParking.model.User;
import com.example.MuhanParking.repository.UserRepository;
import com.example.MuhanParking.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // SignUpDto, LoginDto를 사용하는 부분을 SignUpRequest, LoginRequest로 변경
    public void signup(SignUpRequest request) throws Exception {  // SignUpDto -> SignUpRequest
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new Exception("이미 존재하는 아이디입니다");
        }

        // User 생성 방식을 builder 패턴으로 변경
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
                .build();

        userRepository.save(user);
    }

    public String login(LoginRequest request) throws Exception {  // LoginDto -> LoginRequest
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("아이디 또는 비밀번호가 틀립니다"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 틀립니다");
        }

        return createToken(user);
    }

    private String createToken(User user) {
        // Role enum을 직접 전달
        return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
    }
}