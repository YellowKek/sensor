package com.example.demo.repos;

import com.example.demo.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Long> {

}
