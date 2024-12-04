package com.example.migrosOnlineCase.service;

import org.springframework.stereotype.Service;

public interface GeoService {
    public double calculateDistance(double lat1, double lng1, double lat2, double lng2);
}
