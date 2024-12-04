package com.example.migrosOnlineCase.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CourierLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courierId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MigrosStore store;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public MigrosStore getStore() {
        return store;
    }

    public void setStore(MigrosStore store) {
        this.store = store;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
