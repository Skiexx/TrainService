package com.skiexx.trainservice.controllers;

import com.skiexx.trainservice.dtos.NewTrainDto;
import com.skiexx.trainservice.dtos.TrainDto;
import com.skiexx.trainservice.entities.Company;
import com.skiexx.trainservice.entities.Train;
import com.skiexx.trainservice.entities.TrainType;
import com.skiexx.trainservice.errors.ResourceNotFoundException;
import com.skiexx.trainservice.mappers.NewTrainMapper;
import com.skiexx.trainservice.mappers.TrainMapper;
import com.skiexx.trainservice.repositories.CompanyRepository;
import com.skiexx.trainservice.repositories.TrainRepository;
import com.skiexx.trainservice.repositories.TrainTypeRepository;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/train")
public class TrainController {

    private final TrainRepository trainRepository;

    // region Mappers
    private final TrainMapper trainMapper;
    private final NewTrainMapper newTrainMapper;
    private final TrainTypeRepository trainTypeRepository;
    private final CompanyRepository companyRepository;

    // endregion
    public TrainController(TrainRepository trainRepository,
                           TrainMapper trainMapper, NewTrainMapper newTrainMapper,
                           TrainTypeRepository trainTypeRepository,
                           CompanyRepository companyRepository) {
        this.trainRepository = trainRepository;
        this.trainMapper = trainMapper;
        this.newTrainMapper = newTrainMapper;
        this.trainTypeRepository = trainTypeRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/{id}")
    TrainDto getTrainById(@PathVariable Long id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Train with %d id not found", id)));
        return trainMapper.toDto(train);
    }

    @PostMapping("/new")
    TrainDto createTrain(@RequestBody @NonNull @Valid NewTrainDto newTrainDto) {
        Train train = newTrainMapper.toEntity(newTrainDto);

        // Find object from id and set in new entity
        TrainType trainType = trainTypeRepository.findById(newTrainDto.getTrainTypeId())
                .orElseThrow();
        train.setTrainType(trainType);
        Company company = companyRepository.findById(newTrainDto.getCompanyId()).orElseThrow();
        train.setCompany(company);

        return trainMapper.toDto(trainRepository.save(train));
    }
}
