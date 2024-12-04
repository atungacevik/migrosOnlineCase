package com.example.migrosOnlineCase.service;

import com.example.migrosOnlineCase.entity.CourierLog;
import com.example.migrosOnlineCase.repository.CourierLogRepo;
import com.example.migrosOnlineCase.repository.MigrosStoreRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CourierLogServiceImpl implements CourierLogService{

    private static final Logger log = LoggerFactory.getLogger(CourierLogServiceImpl.class);

    private CourierLogRepo courierLogRepo;

    private MigrosStoreRepo migrosStoreRepo;

    public CourierLogServiceImpl(CourierLogRepo courierLogRepo, MigrosStoreRepo migrosStoreRepo) {
        this.courierLogRepo = courierLogRepo;
        this.migrosStoreRepo = migrosStoreRepo;
    }

    public boolean logEntry(String courierId, String storeName, LocalDateTime timeStamp) {
        log.info("Attempting to log entry for Courier ID: {}, Store Name: {}", courierId, storeName);
        LocalDateTime oneMinuteAgo = null;
        if(timeStamp == null) {
            oneMinuteAgo  = LocalDateTime.now().minus(1, ChronoUnit.MINUTES);
        }else {
            oneMinuteAgo = timeStamp.minus(1, ChronoUnit.MINUTES);
        }
        log.debug("Checking if an entry was already logged within the last minute. Time threshold: {}", oneMinuteAgo);

        boolean alreadyLogged = courierLogRepo
                .findFirstByCourierIdAndStoreNameAndTimestampAfter(courierId, storeName, oneMinuteAgo)
                .isPresent();

        if (alreadyLogged) {
            log.warn("Entry already logged for Courier ID: {}, Store Name: {} within the last minute.", courierId, storeName);
            return false;
        }

        CourierLog logEntry = new CourierLog();
        logEntry.setCourierId(courierId);
        logEntry.setStore(migrosStoreRepo.findByName(storeName));
        logEntry.setTimestamp(timeStamp);
        log.debug("Created new CourierLog: {}", logEntry);

        courierLogRepo.save(logEntry);
        log.info("Successfully logged entry for Courier ID: {}, Store Name: {}", courierId, storeName);

        return true;
    }


}
