package com.sm360.advertising.listing.model.vehicle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Entity

@Table(name = "vehicle_make",
        indexes = @Index(
                name = "idx_make_name",
                columnList = "name",
                unique = true))
public class VehicleMake {
    @Id
    @Column(name = "make_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "make", fetch = FetchType.LAZY)
    private Set<VehicleModel> models;
}
