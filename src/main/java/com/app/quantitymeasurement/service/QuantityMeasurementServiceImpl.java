package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements QuantityMeasurementService {

	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);
    @Autowired
    private QuantityMeasurementRepository repository;
    

    // ================= BASE CONVERSIONS =================

    private double convertLengthToMeter(double value,String unit) {
    	switch(unit) {
    	case "KM": return value*1000;
    	case "M": return value;
    	case "CM":return value/100;
    	default: throw new RuntimeException("Invalid Length Unit");
    	}
    }
    
    private double convertWeightToKg(double value,String unit) {
    	switch(unit) {
    	case "G": return value/1000;
    	case "KG": return value;
    	case "TON": return value*1000;
    	default: throw new RuntimeException("Invalid Weight Unit");
    	}
    }
    
    private double convertVolumeToLiter(double value,String unit) {
    	switch(unit) {
    	case "ML": return value/1000;
    	case "L": return value;
    	case "GALLON": return value*3.785;
    	default: throw new RuntimeException("Invalid Volume Unit");
    	}
    }
    
    private double convertTemperatureToCelsius(double value,String unit) {
    	switch(unit) {
    	case "CELSIUS": return value;
    	case "FAHRENHEIT": return (value - 32) * 5/9;
    	case "KELVIN": return value-273.15;
    	default: throw new RuntimeException("Invalid Temperature Unit");
    	}
    }

    // ================= REVERSE CONVERSIONS =================
    
    private double convertMeterToTarget(double value,String unit) {
    	switch(unit) {
    	case "KM": return value/1000;
    	case "M": return value;
    	case "CM": return value*100;
    	default: throw new RuntimeException("Invalid Length Unit");
    	}
    }
    
    private double convertLiterToTarget(double value,String unit) {
    	switch(unit) {
    	case "L": return value;
    	case "ML": return value*1000;
    	case "GALLON": return value/3.785;
    	default: throw new RuntimeException("Invalid Volume Unit");
    	}
    }
    
    private double convertKgToTarget(double value,String unit) {
    	switch(unit) {
    	case "G": return value*1000;
    	case "KG": return value;
    	case "TON": return value/1000;
    	default : throw new RuntimeException("Invalid Weight Unit");
    	}
    }
    
    private double convertCelsiusToTarget(double value,String unit) {
    	switch(unit) {
    	case "CELSIUS": return value;
    	case "FAHRENHEIT": return (value * 9/5)+32;
    	case "KELVIN": return value+273.15;
    	default: throw new RuntimeException("Invalid Temperature Unit");
    	}
    }
    

    // ================= COMMON =================

    private double convertToBase(QuantityDTO dto) {

        switch (dto.getMeasurementType()) {

            case "LENGTH":
                return convertLengthToMeter(dto.getValue(), dto.getUnit());

            case "WEIGHT":
                return convertWeightToKg(dto.getValue(), dto.getUnit());

            case "VOLUME":
                return convertVolumeToLiter(dto.getValue(), dto.getUnit());

            case "TEMPERATURE":
                return convertTemperatureToCelsius(dto.getValue(), dto.getUnit());

            default:
                throw new RuntimeException("Invalid Measurement Type");
        }
    }

    private double convertFromBase(double baseValue, String measurementType, String targetUnit) {

        switch (measurementType) {

            case "LENGTH":
                return convertMeterToTarget(baseValue, targetUnit);

            case "WEIGHT":
                return convertKgToTarget(baseValue, targetUnit);

            case "VOLUME":
                return convertLiterToTarget(baseValue, targetUnit);

            case "TEMPERATURE":
                return convertCelsiusToTarget(baseValue, targetUnit);

            default:
                throw new RuntimeException("Invalid Measurement Type");
        }
    }

    private void validateMeasurementType(QuantityDTO q1, QuantityDTO q2) {
        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Measurement types must be same");
        }
    }
    public String getBaseUnit(String type) {
        switch (type) {
            case "LENGTH": return "M";
            case "WEIGHT": return "KG";
            case "VOLUME": return "L";
            case "TEMPERATURE": return "CELSIUS";
            default: throw new RuntimeException("Invalid Type");
        }
    }

    // ================= MAIN METHOD =================

    @Override
    public QuantityMeasurementDTO operate(QuantityInputDTO input, String operation) {

        validateMeasurementType(
                input.getThisQuantityDTO(),
                input.getThatQuantityDTO()
        );

        double v1 = convertToBase(input.getThisQuantityDTO());
        double v2 = convertToBase(input.getThatQuantityDTO());

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(input.getThisQuantityDTO().getValue());
        entity.setThisUnit(input.getThisQuantityDTO().getUnit());
        entity.setThisMeasurementType(input.getThisQuantityDTO().getMeasurementType());

        entity.setThatValue(input.getThatQuantityDTO().getValue());
        entity.setThatUnit(input.getThatQuantityDTO().getUnit());
        entity.setThatMeasurementType(input.getThatQuantityDTO().getMeasurementType());

        entity.setOperation(operation);

        String type = input.getThisQuantityDTO().getMeasurementType();

        switch (operation) {

            case "ADD":
                entity.setResultValue(v1 + v2);
                entity.setResultUnit(getBaseUnit(type));
                entity.setResultMeasurementType(type);
                logger.info("Successfully Added");
                break;

            case "SUBTRACT":
                entity.setResultValue(v1 - v2);
                entity.setResultUnit(getBaseUnit(type));
                entity.setResultMeasurementType(type);
                logger.info("Successfully Subtracted");
                break;

            case "MULTIPLY":
                entity.setResultValue(v1 * v2);
                entity.setResultUnit(getBaseUnit(type));
                entity.setResultMeasurementType(type);
                logger.info("Successfully Multiplied");
                break;

            case "DIVIDE":
                entity.setResultValue(v1 / v2);
                entity.setResultUnit(getBaseUnit(type));
                entity.setResultMeasurementType(type);
                logger.info("Successfully Divided");
                break;

            case "COMPARE":
                entity.setResultString(String.valueOf(v1 == v2));
                entity.setResultUnit("BOOLEAN");
                entity.setResultMeasurementType("BOOLEAN");
                logger.info("Successfully Compared");
                break;

            case "CONVERT":

                double base = convertToBase(input.getThisQuantityDTO());

                double converted = convertFromBase(
                        base,
                        type,
                        input.getThatQuantityDTO().getUnit()
                );

                entity.setResultValue(converted);
                entity.setResultUnit(input.getThatQuantityDTO().getUnit());
                entity.setResultMeasurementType(type);
                logger.info("Successfully Converted");
                break;

            default:
                throw new RuntimeException("Invalid Operation");
        }

        repository.save(entity);

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(entity.getThisValue());
        dto.setThisUnit(entity.getThisUnit());
        dto.setThisMeasurementType(entity.getThisMeasurementType());

        dto.setThatValue(entity.getThatValue());
        dto.setThatUnit(entity.getThatUnit());
        dto.setThatMeasurementType(entity.getThatMeasurementType());

        dto.setOperation(entity.getOperation());
        dto.setResultValue(entity.getResultValue());
        dto.setResultString(entity.getResultString());

        dto.setResultUnit(entity.getResultUnit());
        dto.setResultMeasurementType(entity.getResultMeasurementType());

        return dto;
    }
    
    @Override
    public List<QuantityMeasurementEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<QuantityMeasurementEntity> getByOperation(String operation) {
        return repository.findByOperation(operation);
    }
}