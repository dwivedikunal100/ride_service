package com.kunal.ride_service.processor;

import com.kunal.ride_service.Constants;
import com.kunal.ride_service.model.CabType;
import com.kunal.ride_service.model.Driver;
import com.kunal.ride_service.model.Location;
import com.kunal.ride_service.pricing.DefaultStrategy;
import com.kunal.ride_service.pricing.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TripProcessor {

    public static HashMap<String, Double> getDriversWithPrices(List<Driver> availableDrivers,Location source, Location destination) {
        // Check if there are no Hatchback driver
        Optional<Driver> hatchBackDriver = availableDrivers.stream().filter(driver -> driver.getCabType().equals(CabType.Hatchback)).findAny();
        HashMap<String, Double> pricesWithDrivers = new HashMap<>();
        Strategy strategy = new DefaultStrategy();
        if (hatchBackDriver.isPresent()) {
            availableDrivers.forEach(driver -> pricesWithDrivers.put(driver.getId(),
                    strategy.price(driver.getCabType(),source, destination, Optional.empty())));
        } else {
            // Pricing for all Hatchback is upgraded to strategy
            availableDrivers.forEach(driver -> pricesWithDrivers.put(driver.getId(),
                    strategy.price(CabType.Hatchback, source, destination, Optional.empty())));
        }
        return pricesWithDrivers;
    }
}
