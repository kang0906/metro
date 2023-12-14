package com.example.metro.map.dto.response;

import com.example.metro.map.entity.SubwayTrack;
import lombok.Data;

@Data
public class SubwayTrackDto {

    private String arrivalStation;
    private String lineName;
    private int cost;
    private boolean isTransfer;
    private String trainTimeTable;

    public SubwayTrackDto(SubwayTrack subwayTrack) {
        this.arrivalStation = subwayTrack.getArrivalStation().getStationName();
        this.lineName = subwayTrack.getLineName();
        this.cost = subwayTrack.getCost();
        this.isTransfer = subwayTrack.isTransfer();
        this.trainTimeTable = subwayTrack.getTrainTimeTable();
    }
}
