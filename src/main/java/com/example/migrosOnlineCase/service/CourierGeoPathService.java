package com.example.migrosOnlineCase.service;

import com.example.migrosOnlineCase.dto.PointDTO;
import com.example.migrosOnlineCase.entity.CourierGeoPath;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourierGeoPathService {
    void addPath(String courierId, PointDTO pointDTO);

    List<CourierGeoPath> getPaths(String courierId);

}
