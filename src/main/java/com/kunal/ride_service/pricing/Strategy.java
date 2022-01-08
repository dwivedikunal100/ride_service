package com.kunal.ride_service.pricing;

import com.kunal.ride_service.model.CabType;
import com.kunal.ride_service.model.Coupon;
import com.kunal.ride_service.model.Location;

import java.util.Optional;


public interface Strategy {

    public Double price(CabType cabType, Location source, Location destination, Optional<Coupon> coupon);
}
