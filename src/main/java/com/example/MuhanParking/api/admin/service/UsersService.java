package com.example.MuhanParking.api.admin.service;


import com.example.MuhanParking.api.admin.dto.request.UserUpdateRequest;
import org.springframework.transaction.annotation.Transactional;
import com.example.MuhanParking.model.User;
import com.example.MuhanParking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private UserRepository userRepository;
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        System.out.println(userRepository);
        return userRepository.findAll();
    }

    public User getDetailUser(String id) {
        try {
            Long parsedId = Long.parseLong(id);
            Optional<User> optionalUser = userRepository.findById(parsedId);
            System.out.println(optionalUser.get());
            if (optionalUser.isPresent()) {
                return optionalUser.get(); 
            } else {
                throw new RuntimeException("사용자를 찾을 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("유효하지 않은 사용자 ID 형식입니다.");
        }
    }

    @Transactional
    public void updateUser(String id, UserUpdateRequest request) {
        try {
            Long userId = Long.parseLong(id);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

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

        } catch (NumberFormatException e) {
            throw new RuntimeException("유효하지 않은 사용자 ID 형식입니다.");
        }
    }
    
}
