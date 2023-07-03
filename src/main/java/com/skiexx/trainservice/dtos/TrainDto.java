package com.skiexx.trainservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Train}
 */
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainDto implements Serializable {
    Long id;
    @Size(max = 5)
    String number;
    Integer year;
    TrainTypeDto trainType;
    CompanyDto company;
    Timestamp circulationStopped;
}