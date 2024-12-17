package com.example.MuhanParking.service;

import com.example.MuhanParking.model.Role;
import com.example.MuhanParking.model.User;
import com.example.MuhanParking.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        createDefaultAdminIfNotExists();
    }

    private void createDefaultAdminIfNotExists() {
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .name("관리자")
                    // 나머지 필수 필드는 기본값(바꾸셔도 됩니다)
                    .phone("000-0000-0000")
                    .address("관리자")
                    .gender("관리자")
                    .studentId(1111)  // 관리자용 학번
                    .department("시스템관리자")
                    .birthDate("0000-00-00")
                    .email("admin@gachon.ac.kr")
                    .build();

            adminUser.setRole(Role.ADMIN);
            userRepository.save(adminUser);
        }
    }
}