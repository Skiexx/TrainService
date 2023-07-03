package com.skiexx.trainservice.mappers;

import com.skiexx.trainservice.dtos.UpdateTrainDto;
import com.skiexx.trainservice.entities.Train;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UpdateTrainMapper {
    Train toEntity(UpdateTrainDto updateTrainDto);

    UpdateTrainDto toDto(Train train);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Train partialUpdate(UpdateTrainDto updateTrainDto, @MappingTarget Train train);
}