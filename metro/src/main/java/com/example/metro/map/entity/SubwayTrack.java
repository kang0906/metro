package com.example.metro.map.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SubwayTrackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departureStationId")
    private SubwayStation departureStation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrivalStationId")
    private SubwayStation arrivalStation;
    private String lineName;
    private boolean isTransfer;
    private String trainTimeTable;
}
