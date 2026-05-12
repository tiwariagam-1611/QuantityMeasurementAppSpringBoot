package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.QuantityMeasurementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.app.quantitymeasurement.model.*;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementService service;

    @GetMapping("/history")
    public List<QuantityMeasurementEntity> getAllHistory() {
        return service.getAll();
    }
    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementEntity> getByOperation(
            @PathVariable String operation) {
        return service.getByOperation(operation.toUpperCase());
    }
    
    @PostMapping("/{operation}")
    public QuantityMeasurementDTO operate(
            @PathVariable String operation,
            @RequestBody QuantityInputDTO input) {

        return service.operate(input, operation.toUpperCase());
    }
}