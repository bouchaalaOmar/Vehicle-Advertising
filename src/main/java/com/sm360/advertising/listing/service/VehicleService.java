package com.sm360.advertising.listing.service;

import com.sm360.advertising.listing.dto.model.VehicleDto;

import java.util.List;

public interface VehicleService {

    /**
     * Get all vehicles
     * @return
     */
    List<VehicleDto> getVehicles();


    /**
     * Add new vehicle
     * @param vehicleDto
     * @return
     */
    VehicleDto addNewVehicle(VehicleDto vehicleDto);

}
