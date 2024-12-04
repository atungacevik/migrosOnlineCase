package com.example.migrosOnlineCase.controller;


import com.example.migrosOnlineCase.dto.DistanceResponseDto;
import org.json.JSONObject;
import com.example.migrosOnlineCase.dto.CourierGeoPathDTO;
import com.example.migrosOnlineCase.service.CourierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier")
public class CourierController {
    private static final Logger log = LoggerFactory.getLogger(CourierController.class);

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping("/stream")
    public ResponseEntity<String> streamCourierLocation(@org.jetbrains.annotations.NotNull @RequestBody CourierGeoPathDTO courierGeoPathDTO) {
        log.info("Stream Courier Location Start");
        if(courierGeoPathDTO.courierId() != null && !courierGeoPathDTO.courierId().isEmpty() ){
            courierService.processLocationStream(courierGeoPathDTO);
        }else{
            log.error("Stream Courier Location Failed.Courier ID must not be empty");
            return ResponseEntity.badRequest()
                    .body("Courier ID must not be empty");
        }
        log.info("Stream Courier Location Successful");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/distance")
    public ResponseEntity<DistanceResponseDto> getTotalTravelDistance(@PathVariable String id) {
        log.info("Get Total Travel Distance By userID Start");

        if (id == null || id.isEmpty()) {
            log.info("Get Total Travel Distance By userID failed! Id cannot be null or empty!");

            // Returning an error response when the ID is invalid
            DistanceResponseDto errorResponse = new DistanceResponseDto();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        // Calculate the total travel distance
        Double totalDistance = courierService.getTotalTravelDistance(id);
        log.info("Get Total Travel Distance By userID Successful");
        // Create a response DTO with the total distance and courierId
        DistanceResponseDto response = new DistanceResponseDto(id, totalDistance);
        log.info("Get Total Travel Distance By userID Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}

