package com.sm360.advertising.listing.model.listing;

import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table
public class Listing {

    /**
     * publishing limit
     */
    public static  int publishingLimit = 2;

    @Id
    @Column(name = "listing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;

    @Column(name = "posting_date")
    private LocalDate postingDate;

    private ListingState state = ListingState.DRAFT;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
