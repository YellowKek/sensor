package com.example.demo.repos;

import com.example.demo.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Long> {
}
