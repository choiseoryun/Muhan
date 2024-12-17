package com.example.MuhanParking.repository;

import com.example.MuhanParking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByStudentId(Integer studentId);
    Optional<User> findByNameAndStudentIdAndBirthDate(String name, Integer studentId, String birthDate);
    Optional<User> findByUsernameAndNameAndStudentId(String username, String name, Integer studentId);
}