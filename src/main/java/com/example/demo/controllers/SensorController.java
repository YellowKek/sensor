package com.example.demo.controllers;

import com.example.demo.models.Sensor;
import com.example.demo.services.SensorService;
import com.example.demo.util.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping("/all")
    public List<Sensor> getAll() {
        return sensorService.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<?> add(@RequestBody @Valid Sensor sensor, BindingResult bindingResult) {
        sensorValidator.validate(sensor, bindingResult);

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/change")
    public void update(@RequestBody @Valid Sensor sensor) {
        sensorService.update(sensor.getId(), sensor);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody long id) {
        sensorService.deleteById(id);
    }
}
