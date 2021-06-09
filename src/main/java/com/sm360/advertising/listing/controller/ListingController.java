package com.sm360.advertising.listing.controller;

import com.sm360.advertising.listing.dto.model.ListingDto;
import com.sm360.advertising.listing.model.listing.ListingState;
import com.sm360.advertising.listing.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/listing")
public class ListingController {

    private static final Logger logger = LoggerFactory.getLogger(ListingService.class);

    @Autowired
    private ListingService listingService;

    /**
     * Create a listing. All the created listings should have state draft by default
     */
    @PostMapping
    public ResponseEntity<ListingDto> createNewListing(@Valid @RequestBody ListingDto listing) {
        return ResponseEntity.ok(listingService.addNewListing(listing));
    }

    /**
     * Update a listing
     */
    @PutMapping(path = "{listingId}")
    public ResponseEntity<ListingDto> updateListing(
            @PathVariable("listingId") Long listingId,
            @Valid @RequestBody ListingDto listingDto) {

        return ResponseEntity.ok(listingService.updateListing(listingId, listingDto));
    }

    /**
     * Get all listings of a dealer with a given state
     */
    @GetMapping
    public ResponseEntity<List<ListingDto>> getListingByDealerAndState(@RequestParam Long dealerId,
                                                                       @RequestParam ListingState state) {
        return ResponseEntity.ok(listingService.getListingByDealerByState(dealerId, state));
    }

    /**
     * Publish a listing
     */
    @PutMapping(path = "/publish/{listingId}")
    public ResponseEntity<ListingDto> publishListing(
            @PathVariable("listingId") Long listingId) {
        return ResponseEntity.ok(listingService.publishListing(listingId));
    }

    /**
     * Unpublish a listing
     */
    @PutMapping(path = "/unpublish/{listingId}")
    public ResponseEntity<ListingDto> unpublishListing(
            @PathVariable("listingId") Long listingId) {
        return ResponseEntity.ok(listingService.unpublishListing(listingId));

    }

    /**
     * Unpublish oldest listing
     */
    @PutMapping(path = "/unpublisholdest/{dealerId}")
    public ResponseEntity<ListingDto> unpublishOldestListing(
            @PathVariable("dealerId") Long dealerId){
        return ResponseEntity.ok(listingService.unpublishOldestListing(dealerId));
    }
}
