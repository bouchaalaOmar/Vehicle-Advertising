package com.sm360.advertising.listing.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleModelDto{

    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private VehicleMakeDto make;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private int year;
}
