package com.example.migrosOnlineCase.service;


import com.example.migrosOnlineCase.dto.PointDTO;
import com.example.migrosOnlineCase.entity.CourierGeoPath;
import com.example.migrosOnlineCase.repository.CourierGeoPathRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourierGeoPathServiceImpl implements CourierGeoPathService{

    private static final Logger log = LoggerFactory.getLogger(CourierGeoPathServiceImpl.class);

    private final CourierGeoPathRepo courierGeoPathRepo;

    public CourierGeoPathServiceImpl(CourierGeoPathRepo courierGeoPathRepo) {
        this.courierGeoPathRepo = courierGeoPathRepo;
    }

    public void addPath(String courierId, PointDTO pointDTO) {
        log.info("Add courier geo path start");

        CourierGeoPath path = new CourierGeoPath();
        path.setCourierId(courierId);
        path.setLat(pointDTO.lat());
        path.setLng(pointDTO.lng());
        path.setTimestamp(LocalDateTime.now());
        CourierGeoPath created = courierGeoPathRepo.save(path);
        if (created != null) {
            log.info("Add courier geo path succeeded. Courier ID: {}", created.getId());
        } else {
            log.warn("Add courier geo path failed. Please check the input or system status.");
        }

    }
    public List<CourierGeoPath> getPaths(String courierId) {
        log.info("Fetching paths for Courier ID: {}", courierId);

        List<CourierGeoPath> paths = courierGeoPathRepo.findByCourierId(courierId);

        if (paths == null || paths.isEmpty()) {
            log.warn("No paths found for Courier ID: {}", courierId);
        } else {
            log.info("Found {} paths for Courier ID: {}", paths.size(), courierId);
        }
        return paths;
    }
    }


