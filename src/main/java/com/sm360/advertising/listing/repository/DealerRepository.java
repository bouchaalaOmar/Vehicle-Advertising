package com.sm360.advertising.listing.repository;

import com.sm360.advertising.listing.model.dealer.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
