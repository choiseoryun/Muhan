package com.example.MuhanParking.api.iot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.example.MuhanParking.model.Statistic;
import com.example.MuhanParking.repository.IotRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.example.MuhanParking.api.iot.dto.request.IotInfoRequest;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IotService {
    private final IotRepository iotRepository;
    private static final Logger logger = LoggerFactory.getLogger(IotService.class);
    private final Map<String, List<IotInfoRequest>> latestIotData = new ConcurrentHashMap<>();

    @Transactional
    public void iotInfo(List<IotInfoRequest> request, String deviceId) {
        logger.info("Received data for device {}: {}", deviceId, request);
        latestIotData.put(deviceId, new ArrayList<>(request));

        int occupiedCount = 0;
        for (IotInfoRequest info : request) {
            if (info.isOccupied()) {
                occupiedCount++;
                logger.debug("Spot {} is occupied in {}", info.getNumber(), deviceId);
            }
        }
        logger.info("Device {}: Total spots: {}, Occupied: {}", deviceId, request.size(), occupiedCount);
    }

    public List<IotInfoRequest> getIotInfo(String deviceId) {
        List<IotInfoRequest> data = latestIotData.get(deviceId);
        if (data == null || data.isEmpty()) {
            data = createDefaultData(deviceId);
            latestIotData.put(deviceId, data);
        }
        logger.info("Returning data for device {}: {}", deviceId, data);
        return data;
    }

    private List<IotInfoRequest> createDefaultData(String deviceId) {
        int spotCount = getSpotCountForDevice(deviceId);
        List<IotInfoRequest> defaultData = new ArrayList<>();

        logger.info("Creating {} parking spots for device: {}", spotCount, deviceId);
        for (int i = 1; i <= spotCount; i++) {
            defaultData.add(new IotInfoRequest(i, false));
        }
        return defaultData;
    }

    private int getSpotCountForDevice(String deviceId) {
        if (deviceId == null) {
            logger.warn("Device ID is null, using default count");
            return 4;
        }

        switch (deviceId.toUpperCase()) {
            case "IOT-A1":
            case "IOT-A2":
                return 34;
            case "IOT-B":
                return 27;
            case "IOT-C":
                return 11;
            default:
                logger.warn("Unknown device ID: {}, using default count", deviceId);
                return 4;
        }
    }

    @Scheduled(fixedRate = 30000)
    public void generateGraph() {
        try {
            System.out.println("emfdjdha");
            ProcessBuilder processBuilder = new ProcessBuilder("/usr/bin/python3",
                    "/home/t24310/MuhanParking/src/main/java/com/example/MuhanParking/api/iot/service/parking_statistics.py");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
            System.out.println("Graph generation completed.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void saveHour(String date, String location, List<IotInfoRequest> iotInfoRequests) {
        System.out.println("입력된 날짜: " + date);
        System.out.println("위치: " + location);
        System.out.println("IoT 정보 요청:");
    
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime parsedDate = LocalDateTime.parse(date, formatter);
        System.out.println("포맷팅된 날짜: " + parsedDate);
    
        // for (IotInfoRequest request : iotInfoRequests) {
        //     String simplifiedLocation = extractLotId(request.getLotId());
        //     System.out.println("IoT 요청 정보: " + request);
        //     Statistic stat = new Statistic();
        //     stat.setLotId(simplifiedLocation);  //
        //     stat.setDate(parsedDate.toLocalDate());
        //     stat.setTime(parsedDate.toLocalTime().toString());
        //     stat.setNumOccupied(request.getNumOccupied());  
        //     stat.setNumAvailable(request.getNumAvailable()); 
        //     stat.setCreatedAt(LocalDateTime.now());
    
        //     // 저장소에 통계 저장
        //     iotRepository.save(stat);  // iotRepository에 데이터 저장
        // }
        
}private String extractLotId(String lotId) {
    if (lotId != null && lotId.length() > 3) {
        return lotId.substring(4, 5); 
    }
    return lotId; 
}}