package com.sm360.advertising.listing.controller;

import com.sm360.advertising.listing.dto.model.VehicleModelDto;
import com.sm360.advertising.listing.model.vehicle.VehicleModel;
import com.sm360.advertising.listing.service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicleModel")
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    /**
     * Get list of models
     * @return
     */
    @GetMapping
    public ResponseEntity<List<VehicleModelDto>> vehicleModels(){
        return ResponseEntity.ok(vehicleModelService.getAllVehicleModels());
    }

    /**
     * Add new model
     * @param vehicleModelDto
     */
    @PostMapping
    public ResponseEntity<VehicleModelDto> addNewModel(@RequestBody  VehicleModelDto vehicleModelDto){
        return ResponseEntity.ok(vehicleModelService.addNewVehicleModel(vehicleModelDto));
    }
}
