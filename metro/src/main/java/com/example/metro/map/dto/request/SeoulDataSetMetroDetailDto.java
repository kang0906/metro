package com.example.metro.map.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeoulDataSetMetroDetailDto {

    private double acmtl;
    private double dstnc;
    private String line;
    private String mnt;
    private String statn_nm;


}
