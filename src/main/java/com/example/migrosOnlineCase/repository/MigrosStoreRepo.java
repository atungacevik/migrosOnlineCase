package com.example.migrosOnlineCase.repository;


import com.example.migrosOnlineCase.entity.MigrosStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MigrosStoreRepo extends JpaRepository<MigrosStore, Long> {
    MigrosStore findByName(String name);
}

