package com.example.MuhanParking.api.iot.controller;

import com.example.MuhanParking.api.iot.dto.IotDevice;
import com.example.MuhanParking.api.iot.dto.request.IotInfoRequest;
import com.example.MuhanParking.api.iot.dto.request.IotStateRequest;
import com.example.MuhanParking.api.iot.service.IotService;
import com.example.MuhanParking.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class IotController {
    private final IotService iotService;

    private List<IotDevice> iotData = List.of(
            new IotDevice(1, "ACTIVE", "342", "3424", "3232"),
            new IotDevice(2, "ACTIVE", "24324", "234241", "34241"),
            new IotDevice(3, "ACTIVE", "24324", "234241", "34241"),
            new IotDevice(4, "ACTIVE", "24324", "234241", "34241"));

    // IoT 데이터만 처리하는 엔드포인트
    // @PostMapping("/iot/info")
    // public ResponseDto<Void> uploadData(@RequestParam("file") MultipartFile file,
    // @RequestParam("json") String jsonData,
    // @RequestHeader("Device-ID") String location) {
    // try {
    // // 1. JSON 문자열을 객체로 변환
    // ObjectMapper mapper = new ObjectMapper();
    // List<IotInfoRequest> iotInfoRequest = mapper.readValue(jsonData,
    // mapper.getTypeFactory().constructCollectionType(List.class,
    // IotInfoRequest.class));

    // // 2. 이미지 파일 저장
    // if (file != null && !file.isEmpty()) {
    // String fileName = file.getOriginalFilename();
    // file.transferTo(new
    // File("/home/t24310/MuhanParking/src/main/resources/static/" + fileName));
    // log.info("Image saved successfully: {}", fileName);
    // }
    // // 3. IoT 데이터 처리
    // if (iotInfoRequest != null && !iotInfoRequest.isEmpty()) {
    // log.info("Processing parking data from device: {}", location);
    // iotInfoRequest.forEach(info -> log.debug("Parking space {}: occupied={}",
    // info.getNumber(), info.isOccupied()));
    // iotInfoRequest.forEach(info -> System.out
    // .println("Parking space " + info.getNumber() + ": occupied=" +
    // info.isOccupied()));
    // iotService.iotInfo(iotInfoRequest, location);
    // }

    // return ResponseDto.success("데이터가 성공적으로 처리되었습니다", null);
    // } catch (Exception e) {
    // log.error("데이터 처리 중 오류 발생: ", e);
    // return ResponseDto.fail(e.getMessage());
    // }
    // }
    // 수림님 보내는 데이터 좀 수정했어요요
    @PostMapping("/iot/info")
    public ResponseDto<Void> uploadData(@RequestParam("file") MultipartFile file,
                                        @RequestParam("json") String jsonData, @RequestParam("traffic") String trafficData,
                                        @RequestParam("latitude") String latitudeData,
                                        @RequestParam("longitude") String longitudeData,
                                        @RequestHeader("Device-ID") String location) {
        try {
            // 1. JSON 문자열을 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            List<IotInfoRequest> iotInfoRequest = mapper.readValue(jsonData,
                    mapper.getTypeFactory().constructCollectionType(List.class, IotInfoRequest.class));

            // 2. 이미지 파일 저장
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                file.transferTo(new File("/home/t24310/MuhanParking/src/main/image/" + fileName));
                log.info("Image saved successfully: {}", fileName);
            }
            // 3. IoT 데이터 처리
            if (iotInfoRequest != null && !iotInfoRequest.isEmpty()) {
                log.info("Processing parking data from device: {}", location);
                iotInfoRequest.forEach(info -> log.debug("Parking space {}: occupied={}",
                        info.getNumber(), info.isOccupied()));
                iotInfoRequest.forEach(info -> System.out
                        .println("Parking space " + info.getNumber() + ": occupied=" + info.isOccupied()));
                iotService.iotInfo(iotInfoRequest, location);
            }
            // 서륜 iot 데이터 저장
            if (iotInfoRequest != null && !iotInfoRequest.isEmpty()) {
                log.info("Processing parking data from device: {}", location);
                System.out.println(location);
                iotInfoRequest.forEach(info -> {
                    Optional<IotDevice> deviceOpt = iotData.stream()
                            .filter(device -> {
                                switch (location) {
                                    case "IoT-A1":
                                        return device.getId() == 1;
                                    case "IoT-A2":
                                        return device.getId() == 2;
                                    case "IoT-B":
                                        return device.getId() == 3;
                                    case "IoT-C":
                                        return device.getId() == 4;
                                    default:
                                        return false;
                                }
                            })
                            .findFirst();
                    deviceOpt.ifPresent(device -> {
                        device.setTraffic(trafficData);
                        device.setLatitude(latitudeData);
                        device.setLongitude(longitudeData);
                        System.out.println("Updated Parking space " + device.getId() + ": occupied=" + device.getStatus() +
                                ", traffic=" + device.getTraffic() + ", latitude=" + device.getLatitude() +
                                ", longitude=" + device.getLongitude());
                    });
                });

                // 수정된 데이터를 처리 (예: 서비스에 전달)
                iotService.iotInfo(iotInfoRequest, location);
            }
            return ResponseDto.success("데이터가 성공적으로 처리되었습니다", null);
        } catch (Exception e) {
            log.error("데이터 처리 중 오류 발생: ", e);
            return ResponseDto.fail(e.getMessage());
        }
    }

    @GetMapping("/iot/info")
    public ResponseDto<List<IotInfoRequest>> getIotInfo(@RequestHeader("Device-ID") String location) {
        try {
            List<IotInfoRequest> parkingData = iotService.getIotInfo(location);
            log.info("Returning parking data for device: {}, spots: {}",
                    location, parkingData.size());
            return ResponseDto.success("성공하였습니다", parkingData);
        } catch (Exception e) {
            log.error("데이터 조회 실패: ", e);
            return ResponseDto.fail(e.getMessage());
        }
    }

    @GetMapping("/iot/location")
    public ResponseDto<List<IotDevice>> getIotData() {
        try {
            return ResponseDto.success("성공하였습니다", iotData);
        } catch (Exception e) {
            log.error("데이터 조회 실패: ", e);
            return ResponseDto.fail(e.getMessage());
        }
    }


    @PutMapping("/{id}/status")
    public ResponseDto<String> updateStatus(@PathVariable int id, @RequestBody IotStateRequest statusRequest) {
        System.out.println("null");
        iotData.forEach(device -> System.out.println("ID: " + device.getId() + ", Status: " + device.getStatus()));
        Optional<IotDevice> deviceOpt = iotData.stream()
                .filter(device -> device.getId() == id)
                .findFirst();

        if (deviceOpt.isPresent()) {
            IotDevice device = deviceOpt.get();
            device.setStatus(statusRequest.getStatus());
            return ResponseDto.success("상태가 성공적으로 변경되었습니다.", null);
        } else {
            return ResponseDto.fail("IoT 장치를 찾을 수 없습니다.");
        }
    }

    @GetMapping("/{id}/status")
    public ResponseDto<IotDevice> getStatus(@PathVariable int id) {
        System.out.println("GET 요청이 들어왔습니다.");
        Optional<IotDevice> deviceOpt = iotData.stream()
                .filter(device -> device.getId() == id)
                .findFirst();
        if (deviceOpt.isPresent()) {
            IotDevice device = deviceOpt.get();
            System.out.println("ID: " + device.getId() + ", Status: " + device.getStatus());
            return ResponseDto.success("상태 조회 성공", device);
        } else {
            return ResponseDto.fail("IoT 장치를 찾을 수 없습니다.");
        }
    }

}
