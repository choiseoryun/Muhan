package com.example.MuhanParking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T2_PARKING_STATISTICS")
public class Statistic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    private int statId;

    @Column(name = "lot_id")
    private String lotId;  
    
    @Column(name = "date")
    private LocalDate date;   
    
    @Column(name = "time")
    private String time;        

    @Column(name = "num_occupied")
    private int numOccupied;  

    @Column(name = "num_available")
    private int numAvailable;    

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시 자동으로 현재 시간 설정
    
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();  // createdAt 값이 null일 경우 현재 시간으로 설정
        }
    }
    
}
