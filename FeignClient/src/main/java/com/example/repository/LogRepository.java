package com.example.repository;

import com.example.entity.RequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<RequestResponse, Integer> {
}
