package com.example.MuhanParking.api.admin.controller;

import com.example.MuhanParking.api.admin.dto.request.UserUpdateRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MuhanParking.model.User;
import com.example.MuhanParking.api.admin.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import com.example.MuhanParking.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/v1/admin/users")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("api/v1/admin/users/{id}")
    public ResponseDto<User> getDetailUser(@PathVariable String id) {
        User user = usersService.getDetailUser(id);
        return ResponseDto.success("사용자 조회 성공", user);
    }

    @PutMapping("api/v1/admin/users/{id}")
    public ResponseDto<Void> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        usersService.updateUser(id, request);
        return ResponseDto.success("사용자 정보가 수정되었습니다", null);
    }

    @GetMapping("/api/v1/admin/image_info")
    public ResponseEntity<byte[]> getImage(@RequestParam String space) throws IOException {
    // 공간 이름에 따른 이미지 경로 설정
        Map<String, String> imagePaths = new HashMap<>();
        imagePaths.put("AI_A_1", "/home/t24310/MuhanParking/src/main/image/A_1_session.jpg");
        imagePaths.put("AI_A_2", "/home/t24310/MuhanParking/src/main/image/A_2_session.jpg");
        imagePaths.put("AI_B_일반", "/home/t24310/MuhanParking/src/main/image/B_session.jpg");
        imagePaths.put("AI_B_장애인", "/home/t24310/MuhanParking/src/main/image/B_session_disable.jpg");
        imagePaths.put("AI_C", "/home/t24310/MuhanParking/src/main/image/C_session.jpg");

        // 요청된 space에 맞는 이미지 경로 찾기
        String filePath = imagePaths.get(space);
        
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()) {
                byte[] imageBytes = Files.readAllBytes(file.toPath());
                return ResponseEntity.ok()
                                    .contentType(MediaType.IMAGE_JPEG)  // 이미지 파일 형식 지정
                                    .body(imageBytes);
            }
        }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 파일이 없으면 404 응답
}

}