package com.skiexx.trainservice.controllers;

import com.skiexx.trainservice.dtos.NewRouteDto;
import com.skiexx.trainservice.dtos.RouteDto;
import com.skiexx.trainservice.entities.Route;
import com.skiexx.trainservice.entities.Train;
import com.skiexx.trainservice.errors.ResourceNotFoundException;
import com.skiexx.trainservice.mappers.NewRouteMapper;
import com.skiexx.trainservice.mappers.RouteMapper;
import com.skiexx.trainservice.repositories.RouteRepository;
import com.skiexx.trainservice.repositories.StationRepository;
import com.skiexx.trainservice.repositories.TrainRepository;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final NewRouteMapper newRouteMapper;
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;

    public RouteController(RouteRepository routeRepository,
                           RouteMapper routeMapper,
                           NewRouteMapper newRouteMapper,
                           TrainRepository trainRepository,
                           StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
        this.newRouteMapper = newRouteMapper;
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
    }

    @GetMapping("/{id}")
    RouteDto getRouteById(@PathVariable Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Route with id %d not found", id)));
        return routeMapper.toDto(route);
    }

    @GetMapping("/find")
    List<RouteDto> findRoutes(
            @And({
                @Spec(path = "train.number", spec = LikeIgnoreCase.class),
                @Spec(path = "startStation.title", spec = LikeIgnoreCase.class),
                @Spec(path = "finalStation.title", spec = LikeIgnoreCase.class),
                @Spec(path = "startStation.city", spec = LikeIgnoreCase.class),
                @Spec(path = "finalStation.city", spec = LikeIgnoreCase.class),
                @Spec(path = "startTimestamp", spec = In.class),
                @Spec(path = "endTimestamp", spec = In.class),
            }) Specification<Route> routeSpecification
            ) {
        return routeRepository.findAll(routeSpecification).stream().map(routeMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/new")
    RouteDto createRoute(@RequestBody @NonNull @Valid NewRouteDto newRouteDto) {
        Route route = newRouteMapper.toEntity(newRouteDto);

        trainRepository.findById(newRouteDto.getTrainId())
                .ifPresent(route::setTrain);
        stationRepository.findById(newRouteDto.getStartStationId())
                .ifPresent(route::setStartStation);
        stationRepository.findById(newRouteDto.getFinalStationId())
                .ifPresent(route::setFinalStation);

        return routeMapper.toDto(routeRepository.save(route));
    }
}
