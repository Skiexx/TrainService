package com.skiexx.trainservice.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Train}
 */
@Value
public class TrainDto implements Serializable {
    Long id;
    @Size(max = 5)
    String number;
    Integer year;
    TrainTypeDto trainType;
    CompanyDto company;
}