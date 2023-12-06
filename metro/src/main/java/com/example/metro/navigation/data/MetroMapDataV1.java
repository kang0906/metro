package com.example.metro.navigation.data;

import com.example.metro.navigation.entity.SubwayStationNode;

import java.util.HashMap;
import java.util.Map;

public class MetroMapDataV1 implements MetroMapData{
    private Map<Long, SubwayStationNode> nodeHashMap = new HashMap<>();    // 정점(지하철역) map

}
