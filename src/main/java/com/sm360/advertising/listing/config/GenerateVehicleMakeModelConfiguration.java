package com.sm360.advertising.listing.config;

import com.sm360.advertising.listing.model.vehicle.VehicleMake;
import com.sm360.advertising.listing.repository.VehicleMakeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class GenerateVehicleMakeModelConfiguration {

    /**
     * If you want to add new brands and new models in database, you Just add new Make in below LinkedHashSet
     * It is just for test. it should managed by ui interface in production.
     */
    private Set<String> makes = Stream.of("Audi", "BMW", "Fiat", "Ford", "Honda", "Jaguar", "Jeep", "Kia", "Maserati",
            "Mazda", "Mitsubitshi", "Nissan", "Volkswagen")
            .collect(Collectors.toCollection(LinkedHashSet::new));

    @Bean
    CommandLineRunner commandLineRunner(
            VehicleMakeRepository vehicleMakeRepository) {
        return args -> {
            for (String make : makes) {

                //save vehicle make
                vehicleMakeRepository.save(
                        new VehicleMake()
                                .setName(make));
            }
        };
    }
}
