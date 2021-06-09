package com.sm360.advertising.listing.service.impl;

import com.sm360.advertising.listing.dto.model.VehicleMakeDto;
import com.sm360.advertising.listing.model.vehicle.VehicleMake;
import com.sm360.advertising.listing.repository.VehicleMakeRepository;
import com.sm360.advertising.listing.service.VehicleMakeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VehicleMakeDto> getMakes() {
//        List<VehicleMake> vehicleMakes =  vehicleMakeRepository.findAll();
//        System.out.println("*******Result start********");
//        for (VehicleMake vehicleMake: vehicleMakes){
//            VehicleMakeDto vehicleMakeDto = modelMapper.map(vehicleMake, VehicleMakeDto.class);
//            System.out.println(vehicleMakeDto.toString());
//        }
//        System.out.println("*******Result finish********");

        return vehicleMakeRepository.findAll()
                .stream()
                .map(vehicleMake -> modelMapper.map(vehicleMake, VehicleMakeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleMakeDto addNewMake(VehicleMakeDto vehicleMakeDto) {
        VehicleMake vehicleMake = modelMapper.map(vehicleMakeDto, VehicleMake.class);
        return modelMapper
                .map(vehicleMakeRepository.save(vehicleMake),
                        VehicleMakeDto.class);
    }
}
