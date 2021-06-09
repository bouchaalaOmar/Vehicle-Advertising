package com.sm360.advertising.listing.service.impl;

import com.sm360.advertising.listing.dto.model.VehicleDto;
import com.sm360.advertising.listing.exception.custumexception.DuplicateEntityException;
import com.sm360.advertising.listing.exception.custumexception.ResourceNotFoundException;
import com.sm360.advertising.listing.model.dealer.Dealer;
import com.sm360.advertising.listing.model.vehicle.Vehicle;
import com.sm360.advertising.listing.model.vehicle.VehicleModel;
import com.sm360.advertising.listing.repository.DealerRepository;
import com.sm360.advertising.listing.repository.VehicleModelRepository;
import com.sm360.advertising.listing.repository.VehicleRepository;
import com.sm360.advertising.listing.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;


    @Override
    public List<VehicleDto> getVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDto addNewVehicle(VehicleDto vehicleDto) {


        //check if vin is already taken
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByVin(vehicleDto.getVin());
        if (vehicleOptional.isPresent()) {
            throw new DuplicateEntityException("vehicle with vin " + vehicleDto.getVin() + " taken");
        }

        //check vehicle model existence
        VehicleModel vehicleModel = vehicleModelRepository.findById(vehicleDto.getVehicleModel().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle model with id " + vehicleDto.getVehicleModel().getId() + " does not exisit"));

        //check dealer existence
        Dealer dealer = dealerRepository.findById(vehicleDto.getDealer().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Dealer with id " + vehicleDto.getDealer().getId() + " does not exisit"));

        Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class)
                .setDealer(dealer)
                .setVehicleModel(vehicleModel);

        return modelMapper
                .map(vehicleRepository.save(vehicle),
                        VehicleDto.class);
    }
}
