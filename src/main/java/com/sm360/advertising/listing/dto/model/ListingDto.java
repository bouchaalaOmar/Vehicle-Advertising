package com.sm360.advertising.listing.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.listing.ListingState;
import com.sm360.advertising.listing.model.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ListingDto {

    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private VehicleDto vehicle;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private DealerDto dealer;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private double price;

    private ListingState state;

    private LocalDate postingDate;

}
