package com.sm360.advertising.listing.model.vehicle;

import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.listing.Listing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(indexes = @Index(
        name = "idx_vehicle_vin",
        columnList = "vin",
        unique = true
))
public class Vehicle {

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vin;

    private String engin;

    private double mileage;

    @Column(name = "exterior_color")
    private String exteriorColor;

    @Column(name = "interior_color")
    private String interiorColor;

    private String transmission;

    @Column(name = "vehicle_state")
    private VehicleState vehicleState;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private VehicleModel vehicleModel;

    @OneToMany(mappedBy = "vehicle")
    private List<Listing> listings;

}
