package com.sm360.advertising.listing.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class DealerDto {

    private Long id;

    private String name;

    private String email;


    private String phone;

    private String address;

    @JsonIgnore
    private Set<VehicleDto> vehicles;

    @JsonIgnore
    private Set<ListingDto> listings;
}
