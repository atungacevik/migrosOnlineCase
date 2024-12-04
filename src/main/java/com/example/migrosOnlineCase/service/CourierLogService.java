package com.example.migrosOnlineCase.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface CourierLogService {


    public boolean logEntry(String courierId, String storeName, LocalDateTime timeStamp);

}
