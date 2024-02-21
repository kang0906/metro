package com.example.metro.realtime.controller;

import com.example.metro.realtime.dto.ArrivalInfoRefreshRequestDto;
import com.example.metro.realtime.service.ArrivalInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArrivalInfoController {

    private final ArrivalInfoService arrivalInfoService;

    @PostMapping("/admin/metro/arrival/check")
    public String ArrivalInfoRefreshRequest(@RequestBody ArrivalInfoRefreshRequestDto requestDto) {
        log.info("ArrivalInfoRefreshRequest : {}", requestDto.getLineName());

        arrivalInfoService.checkArrivalInfoFromSeoulApiBylineName(requestDto.getLineName());

        return "success";
    }

}
