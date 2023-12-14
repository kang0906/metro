package com.example.metro.map.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SeoulDataSetMetroDto {

    private List<SeoulDataSetMetroDetailDto> data = new ArrayList<>();

}
