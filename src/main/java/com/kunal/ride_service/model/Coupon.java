package com.kunal.ride_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coupon {
    String id;
    Integer discount;
}
