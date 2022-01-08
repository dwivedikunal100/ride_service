package com.kunal.ride_service.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Person {
    String id;
    Location location;
    List<Trip> history;

    public Person(String id,Location location){
        this.id = id;
        this.location = location;
        this.history = new ArrayList<>();
    }
}
