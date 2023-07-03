package com.skiexx.trainservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "train_type")
public class TrainType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @OneToMany(mappedBy = "trainType", orphanRemoval = true)
    private Set<Train> trains = new LinkedHashSet<>();

}