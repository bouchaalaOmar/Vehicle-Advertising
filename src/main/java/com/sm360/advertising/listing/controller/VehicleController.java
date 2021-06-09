package com.sm360.advertising.listing.controller;

import com.sm360.advertising.listing.dto.model.VehicleDto;
import com.sm360.advertising.listing.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Get all vehicles
     * @return
     */
    @GetMapping
    public ResponseEntity <List<VehicleDto>> getAllVehicle(){
        return ResponseEntity.ok(vehicleService.getVehicles());
    }

    /**
     * Add new vehicle
     * @param vehicleDto
     * @return
     */
    @PostMapping
    public ResponseEntity<VehicleDto> addNewVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.addNewVehicle(vehicleDto));
    }
}
