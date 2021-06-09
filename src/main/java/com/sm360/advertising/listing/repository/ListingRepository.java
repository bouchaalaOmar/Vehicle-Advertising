package com.sm360.advertising.listing.repository;

import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.listing.Listing;
import com.sm360.advertising.listing.model.listing.ListingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findListingsByDealerAndState(Dealer dealer, ListingState state);
}
