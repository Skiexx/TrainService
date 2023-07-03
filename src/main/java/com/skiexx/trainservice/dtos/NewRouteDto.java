package com.skiexx.trainservice.dtos;

import com.skiexx.trainservice.repositories.StationRepository;
import com.skiexx.trainservice.repositories.TrainRepository;
import com.skiexx.trainservice.validators.ForeignKeyExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Route}
 */
@Value
public class NewRouteDto implements Serializable {
    @Null
    Long id;
    @NotNull
    Timestamp startTimestamp;
    @NotNull
    Timestamp endTimestamp;
    @NotNull
    @ForeignKeyExists(repository = TrainRepository.class, message = "Train with this id does not exist")
    Long trainId;
    @NotNull
    @ForeignKeyExists(repository = StationRepository.class, message = "Station with this id does not exist")
    Long startStationId;
    @NotNull
    @ForeignKeyExists(repository = StationRepository.class, message = "Station with this id does not exist")
    Long finalStationId;
}