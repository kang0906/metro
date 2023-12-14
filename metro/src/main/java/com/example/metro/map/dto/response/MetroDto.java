package com.example.metro.map.dto.response;

import com.example.metro.map.entity.SubwayStation;
import com.example.metro.map.entity.SubwayTrack;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MetroDto {
    private int subwayStationId;
    private String StationName;
    private String lineName;
    private boolean isTransfer;
    private List<SubwayTrackDto> subwayTracks = new ArrayList<>();

    public MetroDto(SubwayStation subwayStation) {
        this.subwayStationId = subwayStation.getSubwayStationId();
        this.StationName = subwayStation.getStationName();
        this.lineName = subwayStation.getLineName();
        this.isTransfer = subwayStation.isTransfer();

        List<SubwayTrack> subwayTracks1 = subwayStation.getSubwayTracks();
        for (SubwayTrack subwayTrack : subwayTracks1) {
            this.subwayTracks.add(new SubwayTrackDto(subwayTrack));
        }
    }
}
