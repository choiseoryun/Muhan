package com.example.MuhanParking.repository;
import com.example.MuhanParking.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotRepository extends JpaRepository<Statistic, Integer> {
    
}
