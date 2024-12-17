package com.example.MuhanParking.api.iot.dto.response;

import com.example.MuhanParking.api.iot.dto.request.IotInfoRequest;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class IotInfoResponse {
    private List<IotInfoRequest> iotInfo;
}
