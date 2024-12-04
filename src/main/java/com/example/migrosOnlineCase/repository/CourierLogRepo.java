package com.example.migrosOnlineCase.repository;

import com.example.migrosOnlineCase.entity.CourierLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourierLogRepo extends JpaRepository<CourierLog, Long> {
    List<CourierLog> findByCourierIdAndStoreId(String courierId, Long storeId);
    Optional<CourierLog> findFirstByCourierIdAndStoreNameAndTimestampAfter(String courierId, String storeName, LocalDateTime timestamp);
}

