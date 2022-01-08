package com.kunal.ride_service.pricing;

import com.kunal.ride_service.model.CabType;
import com.kunal.ride_service.model.Coupon;
import com.kunal.ride_service.model.Location;

import java.util.Optional;

public class DefaultStrategy implements Strategy {

    public final double COMMON_DISTANCE_RATE = 10.0;
    private final int MINIMUM_COMMON_DISTANCE = 10;

    public Double price(CabType cabType, Location source, Location destination, Optional<Coupon> coupon) {
        Double distance = source.distance(destination);
        Double amount = Math.min(distance, MINIMUM_COMMON_DISTANCE) * COMMON_DISTANCE_RATE;
        distance -= Math.min(distance, MINIMUM_COMMON_DISTANCE);
        if (distance > 0) {
            amount += distance * cabType.getRate();
        }
        if (!coupon.isPresent()) {
            return amount > 50.0 ? amount : 50.0;
        } else {
            return Math.min(amount, (amount * coupon.get().getDiscount()) / 100.0);
        }
    }
}
