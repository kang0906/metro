package com.example.metro.map.repository;

import com.example.metro.map.entity.SubwayTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayTrackRepository extends JpaRepository<Integer, SubwayTrack> {
}
