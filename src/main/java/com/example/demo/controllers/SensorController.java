package com.example.demo.controllers;

import com.example.demo.models.Sensor;
import com.example.demo.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sensors")
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/all")
    public List<Sensor> getAll() {
        return sensorService.findAll();
    }

    @PostMapping("/registration")
    public void add(@RequestBody @Valid Sensor sensor) {
        sensorService.save(sensor);
    }

}
