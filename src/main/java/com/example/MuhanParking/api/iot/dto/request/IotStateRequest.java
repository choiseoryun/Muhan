package com.example.MuhanParking.api.iot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IotStateRequest {
    private int id;
    private String status;

    public IotStateRequest() {
    }

    // 파라미터가 있는 생성자
    public IotStateRequest(int id, String status) {
        this.id = id;
        this.status = status;
    }
}