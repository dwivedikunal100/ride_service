package com.kunal.ride_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trip {
    int id;
    String userId;
    String driverId;
    Location source;
    Location destination;
    Double price;
    boolean isActive;
}
