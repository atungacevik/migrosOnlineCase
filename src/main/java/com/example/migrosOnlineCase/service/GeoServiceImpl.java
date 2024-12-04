package com.example.migrosOnlineCase.service;

import com.example.migrosOnlineCase.util.GeoDiffCalculator;
import org.springframework.stereotype.Service;

@Service
public class GeoServiceImpl {

    public double calculateDistanceHaversine(double lat1, double lng1, double lat2, double lng2) {
        return GeoDiffCalculator.calculateDistanceHaversine(lat1, lng1, lat2, lng2);
    }

    public double calculateDistanceVincenty(double lat1, double lng1, double lat2, double lng2) {
        return GeoDiffCalculator.calculateDistanceVincenty(lat1, lng1, lat2, lng2);
    }
}
