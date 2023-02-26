package com.example.demo.services;

import com.example.demo.models.Sensor;
import com.example.demo.repos.SensorRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepo sensorRepo;

    @Autowired
    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public List<Sensor> findAll() {
        return sensorRepo.findAll();
    }

    public Optional<Sensor> findById(long id) {
        return sensorRepo.findById(id);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepo.save(sensor);
    }

    @Transactional
    public void deleteById(long id) {
        sensorRepo.deleteById(id);
    }

    @Transactional
    public void update(long id, Sensor updSensor) {
        updSensor.setId(id);
        save(updSensor);
    }
}
