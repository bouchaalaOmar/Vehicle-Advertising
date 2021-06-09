package com.sm360.advertising.listing.controller;

import com.sm360.advertising.listing.dto.model.VehicleMakeDto;
import com.sm360.advertising.listing.model.vehicle.VehicleMake;
import com.sm360.advertising.listing.service.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicleMake")
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService vehicleMakeService;

    /**
     * Get list of makes
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<VehicleMakeDto>> vehicleMakes() {
        return ResponseEntity.ok(vehicleMakeService.getMakes());
    }

    /**
     * Add new make
     *
     * @param vehicleMakeDto
     */
    @PostMapping
    public ResponseEntity<VehicleMakeDto> addNewMake(@RequestBody VehicleMakeDto vehicleMakeDto) {
        return ResponseEntity.ok(vehicleMakeService.addNewMake(vehicleMakeDto));
    }
}
