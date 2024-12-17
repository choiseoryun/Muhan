package com.example.MuhanParking.api.admin.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String phone;     
    private String email;  
    private String department; 
}
