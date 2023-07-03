package com.skiexx.trainservice.repositories;

import com.skiexx.trainservice.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}