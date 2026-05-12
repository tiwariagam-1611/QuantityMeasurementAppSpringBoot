package com.app.quantitymeasurement.service;

import java.util.List;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.model.*;

public interface QuantityMeasurementService {
    QuantityMeasurementDTO operate(QuantityInputDTO input, String operation);
    List<QuantityMeasurementEntity> getAll();

    List<QuantityMeasurementEntity> getByOperation(String operation);
}