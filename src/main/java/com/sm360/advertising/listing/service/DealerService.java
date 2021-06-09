package com.sm360.advertising.listing.service;

import com.sm360.advertising.listing.dto.model.DealerDto;
import com.sm360.advertising.listing.model.dealer.Dealer;

import java.util.List;

public interface DealerService {

    /**
     * Get all dealers
     * @return
     */
    List<DealerDto> getAllDealers();


    /**
     * Add a new dealer
     * @param dealerDto
     * @return
     */
    DealerDto addNewDealer(DealerDto dealerDto);
}
