package com.sm360.advertising.listing.service.impl;

import com.sm360.advertising.listing.dto.model.VehicleModelDto;
import com.sm360.advertising.listing.model.vehicle.Vehicle;
import com.sm360.advertising.listing.model.vehicle.VehicleMake;
import com.sm360.advertising.listing.model.vehicle.VehicleModel;
import com.sm360.advertising.listing.repository.VehicleMakeRepository;
import com.sm360.advertising.listing.repository.VehicleModelRepository;
import com.sm360.advertising.listing.service.VehicleModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VehicleModelDto> getAllVehicleModels() {
       return vehicleModelRepository.findAll()
                .stream()
                .map(vehicleModel -> modelMapper.map(vehicleModel, VehicleModelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleModelDto addNewVehicleModel(VehicleModelDto vehicleModelDto) {
        //todo check vehicle model existence
        VehicleMake vehicleMake = vehicleMakeRepository.findById(vehicleModelDto.getMake().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "vehicle make with id " + vehicleModelDto.getMake().getId() + "does not exisit"));

        VehicleModel vehicleModel = modelMapper.map(vehicleModelDto, VehicleModel.class)
                .setMake(vehicleMake);

        System.out.println("vehicleModel : "+vehicleModel.toString());
        return modelMapper
                .map(vehicleModelRepository.save(vehicleModel),
                        VehicleModelDto.class);
    }
}
