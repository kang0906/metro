package com.example.metro.map.repository;

import com.example.metro.map.entity.SubwayStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayStationRepository extends JpaRepository<Integer, SubwayStation> {
}
