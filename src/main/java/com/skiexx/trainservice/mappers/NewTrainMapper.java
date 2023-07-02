package com.skiexx.trainservice.mappers;

import com.skiexx.trainservice.dtos.NewTrainDto;
import com.skiexx.trainservice.entities.Train;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewTrainMapper {
    Train toEntity(NewTrainDto newTrainDto);

    NewTrainDto toDto(Train train);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Train partialUpdate(NewTrainDto newTrainDto, @MappingTarget Train train);
}