package com.skiexx.trainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "train_station_visit")
public class TrainStationVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "visit_timestamp", nullable = false)
    private Timestamp visitTimestamp;

    @Column(name = "leave_timestamp")
    private Timestamp leaveTimestamp;

}
