package com.example.migrosOnlineCase.dto;

public class DistanceResponseDto {
    String courierId;
    Double totalDistance;

    public DistanceResponseDto(String courierId, Double totalDistance) {
        this.courierId = courierId;
        this.totalDistance = totalDistance;
    }

    public DistanceResponseDto() {
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
