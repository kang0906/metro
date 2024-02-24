package com.example.metro.realtime.entity;

import com.example.metro.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SeoulApiRequestLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seoulApiRequestLogId;

    private String serviceName;
    private String args;

    public SeoulApiRequestLog(String serviceName, String args) {
        this.serviceName = serviceName;
        this.args = args;
    }
}
