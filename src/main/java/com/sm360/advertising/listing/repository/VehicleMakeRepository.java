package com.sm360.advertising.listing.repository;

import com.sm360.advertising.listing.model.vehicle.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long> {
}
