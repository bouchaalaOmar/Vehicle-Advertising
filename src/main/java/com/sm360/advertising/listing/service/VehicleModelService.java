package com.sm360.advertising.listing.service;

import com.sm360.advertising.listing.dto.model.VehicleModelDto;
import com.sm360.advertising.listing.model.vehicle.VehicleMake;
import com.sm360.advertising.listing.model.vehicle.VehicleModel;

import java.util.List;

public interface VehicleModelService {

    /**
     * Get all makes
     */
    List<VehicleModelDto> getModels();

    /**
     * Add a new make
     */
    VehicleModelDto addNewModel(VehicleModelDto model);
}
