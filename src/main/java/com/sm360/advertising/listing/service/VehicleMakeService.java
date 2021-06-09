package com.sm360.advertising.listing.service;

import com.sm360.advertising.listing.dto.model.VehicleMakeDto;
import com.sm360.advertising.listing.model.vehicle.VehicleMake;

import java.util.List;

public interface VehicleMakeService {

    /**
     * Get all makes
     */
    List<VehicleMakeDto> getMakes();

    /**
     * Add a new make
     */
    VehicleMakeDto addNewMake(VehicleMakeDto vehicleMake);
}
