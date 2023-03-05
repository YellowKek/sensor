package com.example.demo.services;

import com.example.demo.models.Measurement;
import com.example.demo.repos.MeasurementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepo measurementRepo;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepo measurementRepo, SensorService sensorService) {
        this.measurementRepo = measurementRepo;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
        measurementRepo.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepo.findAll();
    }

    public Optional<Measurement> findById(long id) {
        return measurementRepo.findById(id);
    }

    @Transactional
    public void update(long id, Measurement updMeasurement) {
        updMeasurement.setId(id);
        measurementRepo.save(updMeasurement);
    }

}
