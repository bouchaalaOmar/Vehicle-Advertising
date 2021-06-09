package com.sm360.advertising.listing.service.impl;

import com.sm360.advertising.listing.dto.model.ListingDto;
import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.listing.Listing;
import com.sm360.advertising.listing.model.listing.ListingState;
import com.sm360.advertising.listing.model.vehicle.Vehicle;
import com.sm360.advertising.listing.repository.DealerRepository;
import com.sm360.advertising.listing.repository.ListingRepository;
import com.sm360.advertising.listing.repository.VehicleRepository;
import com.sm360.advertising.listing.service.ListingService;
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
public class ListingServiceImpl implements ListingService {

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

        //check dealer
        Dealer dealer = dealerRepository.findById(listingDto.getDealer().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Dealer with id " + listingDto.getDealer().getId() + " does not exisit"));

        //check vehicle
        Vehicle vehicle = vehicleRepository.findById(listingDto.getVehicle().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Vehicle with id " + listingDto.getVehicle().getId() + " does not exisit"));

        //create new Listing with Draft state
        Listing listing = modelMapper.map(listingDto, Listing.class)
                .setState(ListingState.DRAFT)
                .setVehicle(vehicle)
                .setDealer(dealer);

        //save and convert to dto
        return modelMapper
                .map(listingRepository.save(listing),
                        ListingDto.class);
    }

    @Override
    @Transactional
    public ListingDto updateListing(Long listingId, ListingDto listingDto) {
        //check listing id
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalStateException(
                        "listing with id " + listingDto.getId() + "does not exisit"));

        //check vehicle
        Vehicle vehicle = vehicleRepository.findById(listingDto.getVehicle().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Vehicle with id " + listingDto.getVehicle().getId() + " does not exisit"));

        listing.setPrice(listingDto.getPrice());
        listing.setVehicle(vehicle);
        return modelMapper
                .map(listing,
                        ListingDto.class);
    }

    @Override
    public List<ListingDto> getListingByDealerByState(Long dealer_id, ListingState state) {
        Dealer dealer = dealerRepository.findById(dealer_id)
                .orElseThrow(() -> new IllegalStateException(
                        "Dealer with id " + dealer_id + " does not exisit"));

        return listingRepository.findListingsByDealerAndState(dealer, state)
                .stream()
                .map(listing -> modelMapper.map(listing, ListingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ListingDto publishListing(Long listingId) {
        // change state to published and change the posting date
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalStateException(
                        "listing with id " + listingId + "does not exisit"));

        //check publishing Limit
        int sizeOfPublishedListings = getListingByDealerByState(listing.getDealer().getId(), ListingState.PUBLISHED)
                .size();

        if(sizeOfPublishedListings == Listing.publishingLimit){
            throw new IllegalStateException("dealer's tier of publishing limit was reached");
        }

        //change state to published
        listing.setState(ListingState.PUBLISHED);
        listing.setPostingDate(LocalDate.now());

        return modelMapper
                .map(listing,
                        ListingDto.class);
    }

    @Override
    @Transactional
    public ListingDto unpublishOldestListing(Long dealerId){

        //find the oldest published listing  by post date
        ListingDto oldestByPostDate = getListingByDealerByState(dealerId, ListingState.PUBLISHED)
                .stream()
                .min(Comparator.comparing(ListingDto::getPostingDate))
                .orElseThrow(NoSuchElementException::new);

        //call unpublish listing
        return unpublishListing(oldestByPostDate.getId());
    }

    @Override
    @Transactional
    public ListingDto unpublishListing(Long listingId) {
        // change state to draft
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalStateException(
                        "listing with id " + listingId + "does not exisit"));

        listing.setState(ListingState.DRAFT);

        return modelMapper
                .map(listing,
                        ListingDto.class);
    }

}
