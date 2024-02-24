package com.example.metro.realtime.repository;

import com.example.metro.realtime.entity.SeoulApiRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SeoulApiRequestLogRepository extends JpaRepository<SeoulApiRequestLog, Long> {

    List<SeoulApiRequestLog> findAllByCreatedDateTimeAfter(LocalDateTime start);
}
