package com.skiexx.trainservice.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Company}
 */
@Value
public class CompanyDto implements Serializable {
    Long id;
    @Size(max = 50)
    String title;
    @Size(max = 10)
    String smallTitle;
}