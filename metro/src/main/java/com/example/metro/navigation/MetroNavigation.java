package com.example.metro.navigation;

import com.example.metro.navigation.data.MetroMapData;
import com.example.metro.navigation.entity.MetroCourse;
import com.example.metro.navigation.entity.SubwayEdge;
import com.example.metro.navigation.entity.SubwayStationNode;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class MetroNavigation {

    private final MetroMapData metroMapData;    // 정점(지하철역) map

    /**
     * 지하철 길찾기 기능
     *
     * @param start       출발지
     * @param destination 도착지
     * @return 최소 경로
     */
    public MetroCourse navigateMetro(int start, int destination) {

        // 노드 방문 기록
        HashMap<Integer, MetroCourse> visitedNodeMap = new HashMap<>();

        // 현재 라운드 탐색 map
        HashMap<Integer, MetroCourse> nextNodeMap = new HashMap<>();

        // 다음 라운드 탐색 map
        HashMap<Integer, MetroCourse> newNodeMap = new HashMap<>();
        SubwayStationNode startNode = metroMapData.findNodeByNodeId(start);
        nextNodeMap.put(startNode.getSubwayStationNodeId(), new MetroCourse(startNode, 0));

        MetroCourse toDestination = null;
        while (toDestination == null) {
            for (Integer stationId : nextNodeMap.keySet()) {

                SubwayStationNode currentNode = metroMapData.findNodeByNodeId(stationId);
                MetroCourse currentMetroCourse = nextNodeMap.get(stationId);

                if (currentNode.getSubwayStationNodeId() == destination) {
                    toDestination = currentMetroCourse;
                }

                List<SubwayEdge> subwayEdgeList = currentNode.getSubwayEdgeList();
                for (SubwayEdge subwayEdge : subwayEdgeList) {
                    if (subwayEdge.isTransfer()) {   // 환승 하는 경우
                        // todo : 구현
                    } else {     //환승이 아닌 경우

                        MetroCourse visitedMetroCourse = visitedNodeMap.get(subwayEdge.getDestinationSubwayStationNode().getSubwayStationNodeId());

                        // 전에 노드를 방문한적이 없거나 (null) 새로 찾은 경로가 더 빠르다면 다음 탐색 map에 추가
                        if (visitedMetroCourse == null && visitedMetroCourse.getTotalCost() > currentMetroCourse.getTotalCost() + subwayEdge.getCost()) {
                            newNodeMap.put(subwayEdge.getDestinationSubwayStationNode().getSubwayStationNodeId(), new MetroCourse(currentMetroCourse, subwayEdge));
                            visitedNodeMap.put(subwayEdge.getDestinationSubwayStationNode().getSubwayStationNodeId(),
                                    new MetroCourse(currentMetroCourse, subwayEdge));
                        }
                    }
                }
            }
        }

        return toDestination;
    }

}
