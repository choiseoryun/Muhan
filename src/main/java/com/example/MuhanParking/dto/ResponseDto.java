package com.example.MuhanParking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {  // 제네릭으로 만들어서 재사용 가능하게
    private boolean success;
    private String message;
    private T data;  // 데이터 타입을 유연하게

    // 성공 응답용
    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(true, message, data);
    }

    // 실패 응답용
    public static <T> ResponseDto<T> fail(String message) {
        return new ResponseDto<>(false, message, null);
    }
}
