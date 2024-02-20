package com.example.metro.navigation.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MetroCourse {

    private List<Integer> course = new ArrayList<>();
    private int totalCost = 0;

    /**
     * @param node 출발역
     * @param startCost 출발역 대기시간 (열차도착까지)
     */
    public MetroCourse(SubwayStationNode node, int startCost) {
        this.course.add(node.getSubwayStationNodeId());
        this.totalCost += startCost;
    }

    public MetroCourse(MetroCourse metroCourse, SubwayEdge subwayEdge) {
        course = new ArrayList<>(metroCourse.getCourse());
        totalCost = metroCourse.getTotalCost();
        addCourse(subwayEdge);
    }

    private int addCourse(SubwayEdge edge) {
        course.add(edge.getDestinationSubwayStationNode().getSubwayStationNodeId());
        totalCost += edge.getCost();
        return totalCost;
    }
}
