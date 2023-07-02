package com.skiexx.trainservice.repositories;

import com.skiexx.trainservice.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}