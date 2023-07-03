package com.skiexx.trainservice.dtos;

import com.skiexx.trainservice.repositories.CompanyRepository;
import com.skiexx.trainservice.repositories.TrainTypeRepository;
import com.skiexx.trainservice.validators.ForeignKeyExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.skiexx.trainservice.entities.Train}
 */
@Value
public class UpdateTrainDto implements Serializable {
    @Null
    Long id;
    @Size(max = 5)
    String number;
    Integer year;
    @ForeignKeyExists(
            repository = CompanyRepository.class,
            message = "Company with that id doesnt exists"
    )
    Long companyId;
    @ForeignKeyExists(
            repository = TrainTypeRepository.class,
            message = "Train type with that id doesnt exists"
    )
    Long trainTypeId;
}