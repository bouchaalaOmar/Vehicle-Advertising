package com.sm360.advertising.listing.controller;

import com.sm360.advertising.listing.dto.model.DealerDto;
import com.sm360.advertising.listing.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dealer")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    /**
     * Get all dealers
     * @return
     */
    @GetMapping
    public ResponseEntity<List<DealerDto>> getAllDealers(){
        return ResponseEntity.ok(dealerService.getAllDealers());
    }


    /**
     * Add a new dealer
     * @param dealerDto
     */
    @PostMapping
    public ResponseEntity<DealerDto> registerNewDealer(@RequestBody DealerDto dealerDto){
        return ResponseEntity.ok(dealerService.addNewDealer(dealerDto));

    }
}
