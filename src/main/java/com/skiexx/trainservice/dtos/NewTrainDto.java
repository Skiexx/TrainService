package com.skiexx.trainservice.dtos;

import com.skiexx.trainservice.repositories.CompanyRepository;
import com.skiexx.trainservice.repositories.TrainTypeRepository;
import com.skiexx.trainservice.validators.ForeignKeyExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Train}
 */
@Value
public class NewTrainDto implements Serializable {
    @NotNull
    @Size(max = 5)
    String number;
    @NotNull
    Integer year;
    @NotNull()
    @ForeignKeyExists(
        repository = CompanyRepository.class,
        message = "Company with that id doesnt exists"
    )
    Long companyId;
    @NotNull()
    @ForeignKeyExists(
        repository = TrainTypeRepository.class,
        message = "Train type with that id doesnt exists"
    )
    Long trainTypeId;
}