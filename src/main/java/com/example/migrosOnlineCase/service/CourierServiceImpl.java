package com.example.migrosOnlineCase.service;

import com.example.migrosOnlineCase.controller.CourierController;
import com.example.migrosOnlineCase.dto.CourierGeoPathDTO;
import com.example.migrosOnlineCase.dto.MigrosStoreDTO;
import com.example.migrosOnlineCase.dto.PointDTO;
import com.example.migrosOnlineCase.entity.CourierGeoPath;
import com.example.migrosOnlineCase.repository.MigrosStoreRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourierServiceImpl implements CourierService {

    private static final Logger log = LoggerFactory.getLogger(CourierServiceImpl.class);

    private final MigrosStoreRepo migrosStoreRepo;

    private final CourierLogService courierLogService;

    private final CourierGeoPathService courierGeoPathService;

    private final GeoServiceImpl geoService;

    public CourierServiceImpl(MigrosStoreRepo migrosStoreRepo, CourierLogService courierLogService, CourierGeoPathService courierGeoPathService, GeoServiceImpl geoService) {
        this.migrosStoreRepo = migrosStoreRepo;
        this.courierLogService = courierLogService;
        this.courierGeoPathService = courierGeoPathService;
        this.geoService = geoService;
    }


    public void processLocationStream(CourierGeoPathDTO courierGeoPathDTO) {
        log.info("Process Location Stream start");
        List<MigrosStoreDTO> stores = migrosStoreRepo.findAll()
                .stream()
                .map(store -> new MigrosStoreDTO(store.getName(), store.getLat(), store.getLng()))
                .toList();

        for (MigrosStoreDTO store : stores) {
            double distance = geoService.calculateDistanceHaversine(
                    courierGeoPathDTO.lat(), courierGeoPathDTO.lng(),
                    store.lat(), store.lng()
            );
            boolean logged = false;
            if (distance <= 100) {
                if(courierGeoPathDTO.timestamp() == null){
                    logged = courierLogService.logEntry(courierGeoPathDTO.courierId(), store.name(), LocalDateTime.now());
                }else{
                    logged = courierLogService.logEntry(courierGeoPathDTO.courierId(), store.name(), courierGeoPathDTO.timestamp());
                }
                if (logged) {
                    log.info("Courier entered store radius: " + store.name());
                }
            }
        }

        PointDTO currentPointDTO = new PointDTO(courierGeoPathDTO.lat(), courierGeoPathDTO.lng());
        courierGeoPathService.addPath(courierGeoPathDTO.courierId(), currentPointDTO);
        log.info("Process Location Stream end");

    }

    public Double getTotalTravelDistance(String courierId) {
        log.info("Calculating total travel distance for Courier ID: {}", courierId);
        List<CourierGeoPath> courierGeoPaths = courierGeoPathService.getPaths(courierId);
        if (courierGeoPaths == null || courierGeoPaths.isEmpty()) {
            log.warn("No geo paths found for Courier ID: {}", courierId);
            return 0.0;
        }
        log.debug("Retrieved {} geo paths for Courier ID: {}", courierGeoPaths.size(), courierId);
        double totalDistance = 0.0;
        for (int i = 1; i < courierGeoPaths.size(); i++) {
            PointDTO prev = new PointDTO(courierGeoPaths.get(i - 1).getLat(), courierGeoPaths.get(i - 1).getLng());
            PointDTO current = new PointDTO(courierGeoPaths.get(i).getLat(), courierGeoPaths.get(i).getLng());

            double distance = geoService.calculateDistanceHaversine(prev.lat(), prev.lng(), current.lat(), current.lng());
            log.debug("Calculated distance between point {} and {}: {} km", i - 1, i, distance);

            totalDistance += distance;
        }
        log.info("Total travel distance for Courier ID: {} is {} km", courierId, totalDistance);
        return totalDistance;
    }

}
