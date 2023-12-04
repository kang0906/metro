package com.example.metro.Navigation.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayStationNode {

    private List<SubwayEdge> subwayEdgeList = new ArrayList<>();
}
