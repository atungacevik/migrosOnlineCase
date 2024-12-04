package com.example.migrosOnlineCase.service;

import com.example.migrosOnlineCase.MigrosOnlineCaseApplication;
import com.example.migrosOnlineCase.entity.MigrosStore;
import com.example.migrosOnlineCase.repository.MigrosStoreRepo;
import com.example.migrosOnlineCase.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MigrosStoreServiceImpl {

    private final MigrosStoreRepo migrosStoreRepo;

    private ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(MigrosStoreServiceImpl.class);

    public MigrosStoreServiceImpl(MigrosStoreRepo migrosStoreRepo) {
        this.migrosStoreRepo = migrosStoreRepo;
    }

    @PostConstruct
    public void init() throws IOException {
        log.info("MigrosStore creation start");
        if (migrosStoreRepo.count() == 0) {
                List<MigrosStore> stores = objectMapper.readValue(
                        new ClassPathResource("stores.json").getInputStream(),
                        new TypeReference<List<MigrosStore>>() {}
                );
            migrosStoreRepo.saveAll(stores);
            }
        log.info("MigrosStore creation finish");

    }
}
