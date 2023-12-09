package com.example.metro.navigation;

import com.example.metro.navigation.entity.SubwayEdge;
import com.example.metro.navigation.entity.SubwayStationNode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MetroCourse {

    private List<Integer> course = new ArrayList<>();
    private int totalCost = 0;

    public MetroCourse(SubwayStationNode node) {
        course.add(node.getSubwayStationNodeId());
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
