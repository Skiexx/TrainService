package com.skiexx.trainservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "train", uniqueConstraints = {
        @UniqueConstraint(name = "uc_train_entity", columnNames = {"number", "year", "train_type_id", "company_id"})
})
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 5)
    @Column(name = "number", length = 5, nullable = false)
    private String number;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "train_type_id", nullable = false)
    private TrainType trainType;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "circulation_stopped")
    private Timestamp circulationStopped;

    @OneToMany(mappedBy = "train", orphanRemoval = true)
    private Set<Route> routes = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Train train = (Train) o;
        return getId() != null && Objects.equals(getId(), train.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}