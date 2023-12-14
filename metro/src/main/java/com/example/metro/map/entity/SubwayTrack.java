package com.example.metro.map.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    private int cost;
    private boolean isTransfer;
    private String trainTimeTable;

    public SubwayTrack(SubwayStation departureStation, SubwayStation arrivalStation, String lineName, boolean isTransfer, int cost) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.lineName = lineName;
        this.isTransfer = isTransfer;
        this.cost = cost;
    }
}
