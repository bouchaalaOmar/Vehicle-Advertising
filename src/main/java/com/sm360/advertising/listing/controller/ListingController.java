package com.sm360.advertising.listing.controller;

import com.sm360.advertising.listing.dto.model.ListingDto;
import com.sm360.advertising.listing.model.listing.ListingState;
import com.sm360.advertising.listing.service.ListingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/listing")
@Log4j2
public class ListingController {

    private final String className = getClass().getName();

    @Autowired
    private ListingService listingService;

    /**
     * Create a listing. All the created listings should have state draft by default
     */
    @PostMapping
    public ResponseEntity<ListingDto> createNewListing(@Valid @RequestBody ListingDto listing) {
        log.info("{}:createNewListing:Init...",className);
        return ResponseEntity.ok(listingService.addNewListing(listing));
    }

    /**
     * Update a listing
     */
    @PutMapping(path = "{listingId}")
    public ResponseEntity<ListingDto> updateListing(
            @PathVariable("listingId") Long listingId,
            @Valid @RequestBody ListingDto listingDto) {
        log.info("{}:updateListing:Init...",className);
        return ResponseEntity.ok(listingService.updateListing(listingId, listingDto));
    }

    /**
     * Get all listings of a dealer with a given state
     */
    @GetMapping
    public ResponseEntity<List<ListingDto>> getListingByDealerAndState(@RequestParam Long dealerId,
                                                                       @RequestParam ListingState state) {
        log.info("{}:getListingByDealerAndState:Init...",className);
        return ResponseEntity.ok(listingService.getListingByDealerByState(dealerId, state));
    }

    /**
     * Publish a listing
     */
    @PutMapping(path = "/publish/{listingId}")
    public ResponseEntity<ListingDto> publishListing(
            @PathVariable("listingId") Long listingId) {
        log.info("{}:publishListing:Init...",className);
        return ResponseEntity.ok(listingService.publishListing(listingId));
    }

    /**
     * Unpublish a listing
     */
    @PutMapping(path = "/unpublish/{listingId}")
    public ResponseEntity<ListingDto> unpublishListing(
            @PathVariable("listingId") Long listingId) {
        log.info("{}:unpublishListing:Init...",className);
        return ResponseEntity.ok(listingService.unpublishListing(listingId));

    }

    /**
     * Unpublish oldest listing
     */
    @PutMapping(path = "/unpublisholdest/{dealerId}")
    public ResponseEntity<ListingDto> unpublishOldestListing(
            @PathVariable("dealerId") Long dealerId){
        log.info("{}:unpublishOldestListing:Init...",className);
        return ResponseEntity.ok(listingService.unpublishOldestListing(dealerId));
    }
}
