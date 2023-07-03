package com.skiexx.trainservice.dtos;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.TimeZone;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Station}
 */
@Value
public class StationDto implements Serializable {
    Long id;
    @Length(max = 50)
    String city;
    @Length(max = 50)
    String title;
    TimeZone timeZone;
}