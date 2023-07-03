package com.skiexx.trainservice.mappers;

import com.skiexx.trainservice.dtos.RouteDto;
import com.skiexx.trainservice.entities.Route;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TrainMapper.class})
public interface RouteMapper {
    Route toEntity(RouteDto routeDto);

    RouteDto toDto(Route route);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Route partialUpdate(RouteDto routeDto, @MappingTarget Route route);
}