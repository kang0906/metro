package com.example.metro.navigation.data;

import com.example.metro.map.entity.SubwayStation;
import com.example.metro.navigation.entity.SubwayStationNode;

public interface MetroMapData {
    SubwayStationNode findNodeByNodeId(Integer nodeId);
}
