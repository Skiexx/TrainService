package com.skiexx.trainservice.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.skiexx.trainservice.entities.TrainType}
 */
@Value
public class TrainTypeDto implements Serializable {
    Long id;
    @Size(max = 30)
    String title;
}