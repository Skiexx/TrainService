package com.skiexx.trainservice.controllers;

import com.skiexx.trainservice.dtos.NewTrainDto;
import com.skiexx.trainservice.dtos.TrainDto;
import com.skiexx.trainservice.dtos.UpdateTrainDto;
import com.skiexx.trainservice.entities.Company;
import com.skiexx.trainservice.entities.Train;
import com.skiexx.trainservice.entities.TrainType;
import com.skiexx.trainservice.errors.ResourceNotFoundException;
import com.skiexx.trainservice.mappers.NewTrainMapper;
import com.skiexx.trainservice.mappers.TrainMapper;
import com.skiexx.trainservice.mappers.UpdateTrainMapper;
import com.skiexx.trainservice.repositories.CompanyRepository;
import com.skiexx.trainservice.repositories.TrainRepository;
import com.skiexx.trainservice.repositories.TrainTypeRepository;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/train")
public class TrainController {

    private final TrainRepository trainRepository;

    // region Mappers
    private final TrainMapper trainMapper;
    private final NewTrainMapper newTrainMapper;
    private final TrainTypeRepository trainTypeRepository;
    private final CompanyRepository companyRepository;
    private final UpdateTrainMapper updateTrainMapper;

    // endregion
    public TrainController(TrainRepository trainRepository,
                           TrainMapper trainMapper, NewTrainMapper newTrainMapper,
                           TrainTypeRepository trainTypeRepository,
                           CompanyRepository companyRepository,
                           UpdateTrainMapper updateTrainMapper) {
        this.trainRepository = trainRepository;
        this.trainMapper = trainMapper;
        this.newTrainMapper = newTrainMapper;
        this.trainTypeRepository = trainTypeRepository;
        this.companyRepository = companyRepository;
        this.updateTrainMapper = updateTrainMapper;
    }

    @GetMapping("/{id}")
    TrainDto getTrainById(@PathVariable Long id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Train with %d id not found", id)));
        return trainMapper.toDto(train);
    }

    @GetMapping("/find")
    List<TrainDto> findTrains(
            @And({
                @Spec(path = "number", spec = LikeIgnoreCase.class),
                @Spec(path = "trainType.title", spec = LikeIgnoreCase.class),
                @Spec(path = "company.smallTitle", spec = LikeIgnoreCase.class),
                @Spec(path = "company.title", spec = LikeIgnoreCase.class),
                @Spec(path = "year", spec = In.class),
                @Spec(path = "circulationStopped", spec = In.class)
            }) Specification<Train> trainSpecification
            ) {
        return trainRepository.findAll(trainSpecification).stream().map(trainMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/new")
    TrainDto createTrain(@RequestBody @NonNull @Valid NewTrainDto newTrainDto) {
        Train train = newTrainMapper.toEntity(newTrainDto);

        // Find object from id and set in new entity
        trainTypeRepository.findById(newTrainDto.getTrainTypeId())
                .ifPresent(train::setTrainType);
        companyRepository.findById(newTrainDto.getCompanyId())
                .ifPresent(train::setCompany);

        return trainMapper.toDto(trainRepository.save(train));
    }

    @PatchMapping("/{id}/update")
    TrainDto updateTrain(@PathVariable Long id, @RequestBody @NonNull @Valid UpdateTrainDto updateTrainDto) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Train with %d id not found", id)));

        updateTrainMapper.partialUpdate(updateTrainDto, train);

        if (updateTrainDto.getTrainTypeId() != null) {
            TrainType trainType = trainTypeRepository.findById(updateTrainDto.getTrainTypeId())
                    .orElseThrow();
            train.setTrainType(trainType);
        }
        if (updateTrainDto.getCompanyId() != null) {
            Company company = companyRepository.findById(updateTrainDto.getCompanyId()).orElseThrow();
            train.setCompany(company);
        }

        return trainMapper.toDto(trainRepository.save(train));
    }

    @DeleteMapping("/{id}/delete")
    TrainDto deleteTrain(@PathVariable Long id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Train with %d id not found", id)));
        if (train.getCirculationStopped() != null) {
            throw new IllegalArgumentException("Train already stopped in circulation");
        }

        train.setCirculationStopped(Timestamp.from(java.time.Instant.now()));
        return trainMapper.toDto(trainRepository.save(train));
    }
}
