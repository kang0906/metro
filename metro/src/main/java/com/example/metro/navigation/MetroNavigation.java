package com.example.metro.navigation;

import com.example.metro.navigation.data.MetroMapData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MetroNavigation {

    private final MetroMapData metroMapData;    // 정점(지하철역) map

    /**
     * 지하철 길찾기 기능
     * @param start 출발지
     * @param destination 도착지
     * @return 최소 경로
     */
    public MetroCourse navigateMetro(Long start, Long destination){

        return null;    // todo : 유효한 값으로 변경 (알고리즘 구현)
    }

}
