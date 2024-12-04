package com.example.migrosOnlineCase.repository;

import com.example.migrosOnlineCase.entity.CourierGeoPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CourierGeoPathRepo extends JpaRepository<CourierGeoPath, Long> {
    List<CourierGeoPath> findByCourierId(String courierId);
}
