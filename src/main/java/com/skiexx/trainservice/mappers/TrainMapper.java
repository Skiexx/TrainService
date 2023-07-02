package com.skiexx.trainservice.mappers;

import com.skiexx.trainservice.dtos.TrainDto;
import com.skiexx.trainservice.entities.Train;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrainMapper {
    Train toEntity(TrainDto trainDto);

    TrainDto toDto(Train train);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Train partialUpdate(TrainDto trainDto, @MappingTarget Train train);
}