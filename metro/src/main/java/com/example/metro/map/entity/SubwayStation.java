package com.example.metro.map.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subwayStationId;
    private String StationName;
    private String lineName;
    private boolean isTransfer;
    @OneToMany(mappedBy = "departureStation")
    private List<SubwayTrack> subwayTracks = new ArrayList<>();

}
