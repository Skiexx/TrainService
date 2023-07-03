package com.skiexx.trainservice.dtos;

import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Route}
 */
@Value
public class RouteDto implements Serializable {
    Long id;
    TrainDto train;
    StationDto startStation;
    StationDto finalStation;
    Timestamp startTimestamp;
    Timestamp endTimestamp;
}