package com.kunal.ride_service.model;


import lombok.Getter;
import lombok.Setter;

public class Driver extends Person{
    @Getter
    @Setter
    boolean isAvailable;
    @Getter
    CabType cabType;

    public Driver(String id,Location location, CabType cabType){
        super(id,location);
        this.isAvailable = true;
        this.cabType = cabType;
    }


}
