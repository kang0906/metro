package com.example.metro.map.service;

import com.example.metro.map.dto.request.SeoulDataSetMetroDetailDto;
import com.example.metro.map.dto.request.SeoulDataSetMetroDto;
import com.example.metro.map.dto.response.MetroDto;
import com.example.metro.map.entity.SubwayStation;
import com.example.metro.map.entity.SubwayTrack;
import com.example.metro.map.repository.SubwayStationRepository;
import com.example.metro.map.repository.SubwayTrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetroDataAccessService {

    private final SubwayStationRepository subwayStationRepository;
    private final SubwayTrackRepository subwayTrackRepository;

    @Transactional
    public String inputSeoulMetroData(SeoulDataSetMetroDto seoulDataSetMetroDto){
        List<SeoulDataSetMetroDetailDto> inputDataList = seoulDataSetMetroDto.getData();
        SeoulDataSetMetroDetailDto previous = null;

        for (SeoulDataSetMetroDetailDto current : inputDataList) {
            log.info("역 정보 등록중 : {}", current.getStatn_nm());

            SubwayStation saveCurrent = subwayStationRepository.findByStationName(current.getStatn_nm());
            if (saveCurrent == null || !saveCurrent.getLineName().equals(current.getLine())) {
                saveCurrent = subwayStationRepository.save(new SubwayStation(current.getStatn_nm(), current.getLine(), false));
            } else {
                log.info("지하철역 중복 저장 시도 : [DB : {}, input data : {} {}]", current, saveCurrent.getStationName(), saveCurrent.getLineName());
            }

            if (previous != null) {   // 첫 반복이 아니라면 간선 등록
                // 간선 등록 이전역 -> 현재역
                SubwayStation savePrevious = subwayStationRepository.findByStationName(previous.getStatn_nm());
                SubwayTrack findSubwayTrack = subwayTrackRepository.findByDepartureStationAndArrivalStation(savePrevious, saveCurrent);
                if (findSubwayTrack == null) {
                    subwayTrackRepository.save(
                            new SubwayTrack(savePrevious, saveCurrent, savePrevious.getLineName(), false, convertTimeFromString(current.getMnt())));
                } else {
                    log.info("지하철 노선 중복 저장 시도 : [DB : {}, input data : {} -> {}]", findSubwayTrack, previous.getStatn_nm(), current.getStatn_nm());
                }
                // 간선 등록 현재역 -> 이전역
                SubwayTrack findSubwayTrackReverse = subwayTrackRepository.findByDepartureStationAndArrivalStation(saveCurrent, savePrevious);
                if (findSubwayTrackReverse == null) {
                    subwayTrackRepository.save(
                            new SubwayTrack(saveCurrent, savePrevious, saveCurrent.getLineName(), false, convertTimeFromString(current.getMnt())));
                } else {
                    log.info("지하철 노선 중복 저장 시도 : [DB : {}, input data : {} -> {}]", findSubwayTrackReverse, previous.getStatn_nm(), current.getStatn_nm());
                }
            }
            previous = current;
        }

        return "success";
    }

    private int convertTimeFromString(String input){
        String[] split = input.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    public List<MetroDto> getAllData() {
        List<SubwayStation> all = subwayStationRepository.findAll();
        List<MetroDto> result = new ArrayList<>();
        for (SubwayStation subwayStation : all) {
            result.add(new MetroDto(subwayStation));
        }
        return result;
    }
}
