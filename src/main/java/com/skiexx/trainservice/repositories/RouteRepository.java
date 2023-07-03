package com.skiexx.trainservice.repositories;

import com.skiexx.trainservice.entities.Route;
import com.skiexx.trainservice.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RouteRepository extends PagingAndSortingRepository<Route, Long>, JpaSpecificationExecutor<Route>,  JpaRepository<Route, Long> {
}