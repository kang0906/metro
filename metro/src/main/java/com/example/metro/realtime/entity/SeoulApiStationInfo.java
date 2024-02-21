package com.example.metro.realtime.entity;

import com.example.metro.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SeoulApiStationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seoulApiStationInfoId;

    private String subwayId;
    private String stationId;
    private String subwayName;
    private String stationName;


}
