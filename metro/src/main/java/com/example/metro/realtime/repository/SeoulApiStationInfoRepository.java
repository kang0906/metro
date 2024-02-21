package com.example.metro.realtime.repository;

import com.example.metro.realtime.entity.SeoulApiStationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeoulApiStationInfoRepository extends JpaRepository<SeoulApiStationInfo, Long> {
    SeoulApiStationInfo findByStationId(String stationId);
    SeoulApiStationInfo findBySubwayId(String subwayId);
    List<SeoulApiStationInfo> findAllBySubwayName(String subwayName);
}
