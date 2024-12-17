package com.example.MuhanParking.api.iot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IotInfoRequest {
    private int number;
    private boolean occupied;

    public IotInfoRequest() {
    }

    public IotInfoRequest(int number, boolean occupied) {
        this.number = number;
        this.occupied = occupied;
    }
}