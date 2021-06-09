package com.sm360.advertising.listing.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm360.advertising.listing.model.vehicle.VehicleModel;
import com.sm360.advertising.listing.model.vehicle.VehicleState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class VehicleDto {

    private Long id;

    private VehicleModelDto vehicleModel;

    private String vin;

    private String engin;

    private double mileage;

    private String exteriorColor;

    private String interiorColor;

    private String transmission;

    private VehicleState vehicleState;

    private DealerDto dealer;

    @JsonIgnore
    private List<ListingDto> listings;

}
