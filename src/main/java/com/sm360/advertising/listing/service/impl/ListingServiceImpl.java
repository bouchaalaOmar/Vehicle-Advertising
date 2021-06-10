package com.sm360.advertising.listing.service.impl;

import com.sm360.advertising.listing.dto.model.ListingDto;
import com.sm360.advertising.listing.exception.custumexception.PublishingLimitReachedException;
import com.sm360.advertising.listing.exception.custumexception.ResourceNotFoundException;
import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.listing.Listing;
import com.sm360.advertising.listing.model.listing.ListingState;
import com.sm360.advertising.listing.model.vehicle.Vehicle;
import com.sm360.advertising.listing.repository.DealerRepository;
import com.sm360.advertising.listing.repository.ListingRepository;
import com.sm360.advertising.listing.repository.VehicleRepository;
import com.sm360.advertising.listing.service.ListingService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ListingServiceImpl implements ListingService {

    private final String className = getClass().getName();

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DealerRepository dealerRepository;


    @Override
    public ListingDto addNewListing(ListingDto listingDto) {

        final String methodName = "addNewListing";

        log.info("{}:{}:Init...",
                className, methodName);

        final Long dealerId = listingDto.getDealer().getId();
        //check dealer
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> {
                    log.error("{}:{}:Dealer with id {} does not exist",
                            className, methodName, dealerId);

                    return new ResourceNotFoundException("Dealer with id " + dealerId + " does not exisit");
                });

        //check vehicle
        final Long vehicleId = listingDto.getVehicle().getId();
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> {
                    log.error("{}:{}:Vehicle with id {} does not exist",
                            className, methodName, vehicleId);
                    return new ResourceNotFoundException(
                            "Vehicle with id " + vehicleId + " does not exisit");
                });

        //create new Listing with Draft state
        Listing listing = modelMapper.map(listingDto, Listing.class)
                .setState(ListingState.DRAFT)
                .setVehicle(vehicle)
                .setDealer(dealer);

        log.debug("{}:{}: Listing ad successfully created:Response:{}",
                className, methodName, listing.toString());

        //save and convert to dto
        return modelMapper
                .map(listingRepository.save(listing),
                        ListingDto.class);
    }

    @Override
    @Transactional
    public ListingDto updateListing(Long listingId, ListingDto listingDto) {

        final String methodName = "updateListing";

        log.info("{}:{}:Init...",
                className, methodName);

        //check listing id
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> {
                    log.error("{}:{}:Listing with id {} does not exist",
                            className, methodName, listingId);
                    return new ResourceNotFoundException(
                            "listing with id " + listingDto.getId() + "does not exisit");
                });


        //check vehicle
        final Long vehicleId = listingDto.getVehicle().getId();
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> {
                    log.error("{}:{}:Vehicle with id {} does not exist",
                            className, methodName, vehicleId);
                    return new ResourceNotFoundException(
                            "Vehicle with id " + listingDto.getVehicle().getId() + " does not exisit");
                });


        listing.setPrice(listingDto.getPrice());
        listing.setVehicle(vehicle);

        log.debug("{}:{}: Listing ad successfully updated:Response:{}",
                className, methodName, listing.toString());

        return modelMapper
                .map(listing,
                        ListingDto.class);
    }

    @Override
    public List<ListingDto> getListingByDealerByState(Long dealer_id, ListingState state) {

        final String methodName = "getListingByDealerByState";

        log.info("{}:{}:Init...",
                className, methodName);

        Dealer dealer = dealerRepository.findById(dealer_id)
                .orElseThrow(() -> {
                    log.error("{}:{}:Dealer with id {} does not exist",
                            className, methodName, dealer_id);

                    return new ResourceNotFoundException(
                            "Dealer with id " + dealer_id + " does not exisit");
                });

        return listingRepository.findListingsByDealerAndState(dealer, state)
                .stream()
                .map(listing -> modelMapper.map(listing, ListingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ListingDto publishListing(Long listingId) {

        final String methodName = "publishListing";

        log.info("{}:{}:Init...",
                className, methodName);

        // change state to published and change the posting date
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> {
                    log.error("{}:{}:Listing with id {} does not exist",
                            className, methodName, listingId);
                    return new ResourceNotFoundException(
                            "listing with id " + listingId + "does not exisit");
                });

        //check publishing Limit
        int sizeOfPublishedListings = getListingByDealerByState(listing.getDealer().getId(), ListingState.PUBLISHED)
                .size();

        if (sizeOfPublishedListings == Listing.publishingLimit) {
            log.error("{}:{}:Dealer's tier of publishing limit was reached",
                    className, methodName);

            throw new PublishingLimitReachedException("dealer's tier of publishing limit was reached");
        }

        //change state to published
        listing.setState(ListingState.PUBLISHED);
        listing.setPostingDate(LocalDate.now());

        log.debug("{}:{}: Listing ad successfully published:Response:{}",
                className, methodName, listing.toString());

        return modelMapper
                .map(listing,
                        ListingDto.class);
    }

    @Override
    @Transactional
    public ListingDto unpublishOldestListing(Long dealerId) {

        final String methodName = "unpublishOldestListing";

        log.info("{}:{}:Init...",
                className, methodName);

        //find the oldest published listing  by post date
        ListingDto oldestByPostDate = getListingByDealerByState(dealerId, ListingState.PUBLISHED)
                .stream()
                .min(Comparator.comparing(ListingDto::getPostingDate))
                .orElseThrow(() -> {
                    log.error("{}:{}:Dealer with id {} does not exist",
                            className, methodName, dealerId);
                    return new NoSuchElementException(
                            "Dealer with id " + dealerId + " does not contain any published list");
                });

        //call unpublish listing
        return unpublishListing(oldestByPostDate.getId());
    }

    @Override
    @Transactional
    public ListingDto unpublishListing(Long listingId) {

        final String methodName = "unpublishListing";

        log.info("{}:{}:Init...",
                className, methodName);

        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> {
                    log.error("{}:{}:Listing with id {} does not exist",
                            className, methodName, listingId);
                    return new ResourceNotFoundException(
                            "listing with id " + listingId + "does not exisit");
                });

        // change state to draft
        listing.setState(ListingState.DRAFT);

        log.debug("{}:{}: Listing ad successfully unpublished:Response:{}",
                className, methodName, listing.toString());

        return modelMapper
                .map(listing,
                        ListingDto.class);
    }
}
