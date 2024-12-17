package com.example.MuhanParking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// exception/ErrorCode.java
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 회원 관련
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    USERNAME_ALREADY_EXISTS("이미 존재하는 아이디입니다."),
    STUDENT_ID_ALREADY_EXISTS("이미 등록된 학번입니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED("만료된 토큰입니다."),
    UNAUTHORIZED_ACCESS("접근 권한이 없습니다."),

    // 입력값 검증 관련
    INVALID_INPUT_VALUE("잘못된 입력값입니다."),
    INVALID_PHONE_NUMBER("잘못된 전화번호 형식입니다."),
    INVALID_STUDENT_ID("잘못된 학번 형식입니다."),
    REQUIRED_FIELD_MISSING("필수 입력값이 누락되었습니다."),

    // 서버 관련
    SERVER_ERROR("서버 오류가 발생했습니다."),
    DATABASE_ERROR("데이터베이스 오류가 발생했습니다."),

    // 기타
    METHOD_NOT_ALLOWED("지원하지 않는 HTTP Method입니다."),
    INVALID_REQUEST("잘못된 요청입니다.");

    private final String message;
}