package com.skiexx.trainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "start_station_id", nullable = false)
    private Station startStation;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "final_station_id", nullable = false)
    private Station finalStation;

    @Column(name = "start_timestamp", nullable = false)
    private Timestamp startTimestamp;

    @Column(name = "end_timestamp", nullable = false)
    private Timestamp endTimestamp;

    @OneToMany(mappedBy = "route", orphanRemoval = true)
    private Set<TrainStationVisit> trainStationVisits = new LinkedHashSet<>();


}
