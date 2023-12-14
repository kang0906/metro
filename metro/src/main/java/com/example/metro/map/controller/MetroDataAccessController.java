package com.example.metro.map.controller;

import com.example.metro.map.dto.request.SeoulDataSetMetroDto;
import com.example.metro.map.dto.response.MetroDto;
import com.example.metro.map.service.MetroDataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MetroDataAccessController {

    private final MetroDataAccessService metroDataAccessService;

    @PostMapping("/metro/seoul/data")
    public String putSeoulMetroData(@RequestBody SeoulDataSetMetroDto seoulDataSetMetroDto){
        log.info("input data size : {}", seoulDataSetMetroDto.getData().size());
        String result = metroDataAccessService.inputSeoulMetroData(seoulDataSetMetroDto);

        return result;
    }

    @GetMapping("/metro/seoul/data")
    public List<MetroDto> getSeoulMetroData(){
        return metroDataAccessService.getAllData();
    }
}
