package com.example.metro.realtime.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArrivalInfoServiceTest {

    @Autowired
    private ArrivalInfoService arrivalInfoService;

    public void getArrivalInfoFromSeoulApi(String stationName) {

    }

    @DisplayName("서울시 실시간 지하철 도착정보 API 요청이 정상적으로 작동하는지 테스트")
    @Rollback(value = false)
    @Test
    void getArrivalInfoFromSeoulApiTest() {
        // given

        // when
//        arrivalInfoService.getArrivalInfoFromSeoulApi("강남");

        // then

    }


}
