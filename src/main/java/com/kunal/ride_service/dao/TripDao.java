package com.kunal.ride_service.dao;

import com.kunal.ride_service.exceptions.trip.TripNotAvailableException;
import com.kunal.ride_service.model.Location;
import com.kunal.ride_service.model.Trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripDao {

    static Map<Integer, Trip> db ;
    static int COUNT = 0;

    public TripDao(){
        db = new HashMap<>();
    }

    public Trip createTrip(String userId, String driverId, Location from, Location to, Double price){
        COUNT++;
        Trip trip = new Trip(COUNT, userId, driverId, from, to, price, true);
        db.put(COUNT, trip);
        return trip;
    }

    public void endTrip(Integer tripId){
        if(!exists(tripId)){
            throw new TripNotAvailableException();
        }
        db.get(tripId).setActive(false);
    }


    public Trip get(Integer tripId){
        if(!exists(tripId)){
            throw new TripNotAvailableException();
        }
        return db.get(tripId);
    }

    public List<Trip> getUserTrips(String userId){
        return db.values().stream().filter(trip -> trip.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public List<Trip> getDriverTrips(String driverId){
        return db.values().stream().filter(trip -> trip.getDriverId().equals(driverId)).collect(Collectors.toList());
    }


    private boolean exists(Integer tripId){
      return  db.containsKey(tripId);
    }

    public void snapshot(){
        System.out.println(db);
    }

}

