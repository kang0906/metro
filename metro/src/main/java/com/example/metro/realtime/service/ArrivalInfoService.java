package com.example.metro.realtime.service;

import com.example.metro.realtime.entity.ArrivalInfoLog;
import com.example.metro.realtime.entity.SeoulApiStationInfo;
import com.example.metro.realtime.repository.ArrivalInfoLogRepository;
import com.example.metro.realtime.repository.SeoulApiStationInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ArrivalInfoService {

    private final SeoulApiStationInfoRepository seoulApiStationInfoRepository;
    private final ArrivalInfoLogRepository arrivalInfoLogRepository;

    @Transactional
    public void checkArrivalInfoFromSeoulApiBylineName(String lineName) {

        // 해당 호선 역정보 전체 조회
        List<SeoulApiStationInfo> allBySubwayName = seoulApiStationInfoRepository.findAllBySubwayName(lineName);

        // 조회한 역 정보를 기반으로 실시간 지하철 API 호출
        for (SeoulApiStationInfo seoulApiStationInfo : allBySubwayName) {
            getArrivalInfoFromSeoulApi(seoulApiStationInfo.getStationName());
            
            // todo : 외부 API 호출 횟수 카운트
            log.info("서울시 API 호출");
        }
    }


    public void getArrivalInfoFromSeoulApi(String stationName) {
        // 실시간 API 호출
        log.debug("getArrivalInfoFromSeoulApi called with stationName : {}", stationName);
        ResponseEntity<String> response = new RestTemplate().exchange(
                "http://swopenAPI.seoul.go.kr/api/subway/5673747a416a696e38386254635544/json/realtimeStationArrival/0/100/" + stationName,
                HttpMethod.GET,
                null,
                String.class);
        // todo : 실시간 API 사용량 소진에 따른 에러응답에 대한 예외 처리

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            log.info("json parse Exception [JsonProcessingException] : {}", e.getMessage());
            throw new RuntimeException(e);
        }
        JsonNode realtimeArrivalList = jsonNode.get("realtimeArrivalList");

        for (int i = 0; i < 100; i++) {
            log.info("call saveArrivalInfoFromJsonNode [{}] time", i);
            if(!saveArrivalInfoFromJsonNode(realtimeArrivalList.get(i))){
                break;
            }
        }
    }

    private boolean saveArrivalInfoFromJsonNode(JsonNode jsonNode) {
        if (jsonNode == null) {
            return false;
        }

        SeoulApiStationInfo nextStation = seoulApiStationInfoRepository.findByStationId(jsonNode.get("statnTid").asText());

        ArrivalInfoLog arrivalInfoLog = ArrivalInfoLog.builder()
                .stationName(jsonNode.get("statnNm").asText())
                .lineName(nextStation.getSubwayName())
                .nextStationName(nextStation.getStationName())
                .arrivalTime(jsonNode.get("barvlDt").asInt())
                .build();
        arrivalInfoLogRepository.save(arrivalInfoLog);

        return true;
    }

}
