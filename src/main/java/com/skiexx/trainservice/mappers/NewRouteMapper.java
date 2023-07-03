package com.skiexx.trainservice.mappers;

import com.skiexx.trainservice.dtos.NewRouteDto;
import com.skiexx.trainservice.entities.Route;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewRouteMapper {
    Route toEntity(NewRouteDto newRouteDto);

    NewRouteDto toDto(Route route);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Route partialUpdate(NewRouteDto newRouteDto, @MappingTarget Route route);
}