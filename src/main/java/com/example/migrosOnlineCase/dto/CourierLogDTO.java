package com.example.migrosOnlineCase.dto;
import java.time.LocalDateTime;
public record CourierLogDTO(String courierId, String storeName, LocalDateTime timestamp) {
}
