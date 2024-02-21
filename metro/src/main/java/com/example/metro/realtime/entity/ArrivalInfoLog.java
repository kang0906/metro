package com.example.metro.realtime.entity;

import com.example.metro.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArrivalInfoLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arrivalInfoLogId;

    private String stationName; // 역 이름
    private String lineName;    // 호선
    private String nextStationName; // 다음 역 이름(방향)
    private int arrivalTime;   // 도착 예정 시간(초) (barvlDt)

    @Builder
    public ArrivalInfoLog(String stationName, String lineName, String nextStationName, int arrivalTime) {
        this.stationName = stationName;
        this.lineName = lineName;
        this.nextStationName = nextStationName;
        this.arrivalTime = arrivalTime;
    }
}
