package com.example.metro.navigation.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SubwayStationNode {

    private int SubwayStationNodeId;
    private List<SubwayEdge> subwayEdgeList = new ArrayList<>();
}
