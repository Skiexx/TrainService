package com.skiexx.trainservice.repositories;

import com.skiexx.trainservice.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}