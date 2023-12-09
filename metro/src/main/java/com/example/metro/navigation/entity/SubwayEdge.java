package com.example.metro.navigation.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SubwayEdge {

    private int cost = 0;
    private boolean isTransfer;
    private SubwayStationNode destinationSubwayStationNode;

    private List<Integer> arrivalTimeTable = new ArrayList<>();

}
