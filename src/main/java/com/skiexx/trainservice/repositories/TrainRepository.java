package com.skiexx.trainservice.repositories;

import com.skiexx.trainservice.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TrainRepository extends PagingAndSortingRepository<Train, Long>, JpaSpecificationExecutor<Train>, JpaRepository<Train, Long> {
}