package com.example.migrosOnlineCase.dto;
import java.time.LocalDateTime;
public record CourierGeoPathDTO(String courierId, double lat, double lng, LocalDateTime timestamp) {
}
