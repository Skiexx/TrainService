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
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Size(max = 10)
    @Column(name = "small_title", length = 10, nullable = false)
    private String smallTitle;

    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private Set<Train> trains = new LinkedHashSet<>();

}