package com.example.metro.map.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subwayStationId;
    private String stationName;
    private String lineName;
    private boolean isTransfer;
    @OneToMany(mappedBy = "departureStation")
    private List<SubwayTrack> subwayTracks = new ArrayList<>();

    public SubwayStation(String stationName, String lineName, boolean isTransfer) {
        this.stationName = stationName;
        this.lineName = lineName;
        this.isTransfer = isTransfer;
    }
}
