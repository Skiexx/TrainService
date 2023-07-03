package com.skiexx.trainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;

@Entity
@Getter
@Setter
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Length(max = 50)
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Length(max = 50)
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "time_zone", nullable = false)
    private TimeZone timeZone;


    @OneToMany(mappedBy = "startStation", orphanRemoval = true)
    private Set<Route> startRoutes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "finalStation", orphanRemoval = true)
    private Set<Route> finalRoutes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "station", orphanRemoval = true)
    private Set<TrainStationVisit> trainStationVisits = new LinkedHashSet<>();

}
