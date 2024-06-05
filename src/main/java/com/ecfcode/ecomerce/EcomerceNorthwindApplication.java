package com.ecfcode.ecomerce;

import com.ecfcode.ecomerce.infrastracture.entities.Territory;
import com.ecfcode.ecomerce.infrastracture.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcomerceNorthwindApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomerceNorthwindApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RegionRepository regionRepository) {
        return args -> {
            // System.out.println("fetch territory lazy...");
            //				List<Territory> territories = region.getTerritories();
            //				territories.forEach(System.out::println);
            regionRepository.findRegionById(1)
                    .ifPresentOrElse(region -> {
                                System.out.println("fetch territory lazy...");
                                List<Territory> territories = region.getTerritories();
                                territories.forEach(System.out::println);
                            }, () -> System.out.println("No territories found")
                    );
        };
    }
}
