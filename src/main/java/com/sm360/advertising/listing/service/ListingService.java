package com.sm360.advertising.listing.service;

import com.sm360.advertising.listing.dto.model.ListingDto;
import com.sm360.advertising.listing.model.listing.ListingState;

import java.util.List;

public interface ListingService {


    /**
     * Add new listing
     *
     * @param listingDto
     * @return
     */
    ListingDto addNewListing(ListingDto listingDto);


    /**
     * Update listing
     *
     * @param listing
     * @return
     */
    ListingDto updateListing(Long listingId, ListingDto listing);


    /**
     * Get all listings of a dealer with a given state
     *
     * @param dealer_id
     * @param state
     * @return
     */
    List<ListingDto> getListingByDealerByState(Long dealer_id, ListingState state);


    /**
     * Publish a listing
     *
     * @param listingId
     */
    ListingDto publishListing(Long listingId);


    /**
     * unpublish oldest Listing
     * @return
     */
    ListingDto unpublishOldestListing(Long dealerId);


    /**
     * Unpublish a listing
     *
     * @param listingId
     */
    ListingDto unpublishListing(Long listingId);
}
