package com.sm360.advertising.listing.service.impl;

import com.sm360.advertising.listing.dto.model.DealerDto;
import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.repository.DealerRepository;
import com.sm360.advertising.listing.service.DealerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DealerDto> getAllDealers() {
        return dealerRepository.findAll()
                .stream()
                .map(dealer -> modelMapper.map(dealer, DealerDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public DealerDto addNewDealer(DealerDto dealerDto) {
        Dealer dealer = modelMapper.map(dealerDto, Dealer.class);
        return modelMapper
                .map(dealerRepository.save(dealer),
                        DealerDto.class);
    }
}
