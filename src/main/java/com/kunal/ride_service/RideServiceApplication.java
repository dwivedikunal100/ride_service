package com.kunal.ride_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RideServiceApplication {

    public static final double ALLOWED_DISTANCE = 10.0;

    public static void main(String[] args) {
        SpringApplication.run(RideServiceApplication.class, args);
    }

}
