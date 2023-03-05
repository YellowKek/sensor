package com.example.demo.controllers;

import com.example.demo.models.Measurement;
import com.example.demo.services.MeasurementService;
import com.example.demo.util.MeasurementErrorResponse;
import com.example.demo.util.MeasurementException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.util.ErrorUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid Measurement measurement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
//            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<Measurement> getAll() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDays")
    public long rainyDays() {
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
