package com.example.metro.Navigation.MetroMepData;

import com.example.metro.Navigation.Entity.SubwayStationNode;

import java.util.HashMap;
import java.util.Map;

public class MetroMapDataV1 implements MetroMapData{
    private Map<Long, SubwayStationNode> nodeHashMap = new HashMap<>();    // 정점(지하철역) map

}
