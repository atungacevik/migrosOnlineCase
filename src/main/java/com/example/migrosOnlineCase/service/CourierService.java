package com.example.migrosOnlineCase.service;


import com.example.migrosOnlineCase.dto.CourierGeoPathDTO;
import org.springframework.stereotype.Service;

public interface CourierService {

    void processLocationStream(CourierGeoPathDTO courierGeoPathDTO);

    public Double getTotalTravelDistance(String courierId);
}
