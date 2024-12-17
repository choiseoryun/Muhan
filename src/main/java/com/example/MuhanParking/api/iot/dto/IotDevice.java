package com.example.MuhanParking.api.iot.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iot_devices")
@Getter
@Setter
@NoArgsConstructor
public class IotDevice {
    @Id  
    private int id;
    private String status;
    private String traffic;
    private String latitude;
    private String longitude;

    public IotDevice(int id, String status, String traffic, String latitude, String longitude) {
        this.id = id;
        this.status = status;
        this.traffic = traffic;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}