package com.skiexx.trainservice.repositories;

import com.skiexx.trainservice.entities.TrainType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainTypeRepository extends JpaRepository<TrainType, Long> {
}