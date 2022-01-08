package com.kunal.ride_service.dao;


import com.kunal.ride_service.RideServiceApplication;
import com.kunal.ride_service.exceptions.driver.DriverAlreadyExistsException;
import com.kunal.ride_service.exceptions.driver.DriverDoesNotExistException;
import com.kunal.ride_service.exceptions.driver.NoDriverAvailableException;
import com.kunal.ride_service.model.CabType;
import com.kunal.ride_service.model.Driver;
import com.kunal.ride_service.model.Location;
import com.kunal.ride_service.model.Trip;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverDao {

   static Map<String, Driver> db;

    public DriverDao() {
        db = new HashMap<>();
    }

    public void create(String driverId, Location location, CabType cabType) {
        if (exists(driverId)) {
            throw new DriverAlreadyExistsException();
        }
        db.put(driverId, new Driver(driverId, location, cabType));
    }

    public boolean exists(String driverId) {
        return db.containsKey(driverId);
    }

    public Driver get(String driverId) {
        if (!exists(driverId)) {
            throw new DriverDoesNotExistException();
        }
        return db.get(driverId);
    }

    public void update(String driverId, Location location){
        if (!exists(driverId)) {
            throw new DriverDoesNotExistException();
        }
        db.get(driverId).setLocation(location);
    }

    public void setAvailability(String driverId,boolean isAvailable){
        if (!exists(driverId)) {
            throw new DriverDoesNotExistException();
        }
        db.get(driverId).setAvailable(isAvailable);
    }

    public List<Driver> getDriversNearLocation(Location location){
        List<Driver> drivers = db.values().stream().filter(driver -> isValidDistance(driver,location)).collect(Collectors.toList());
        if(drivers.isEmpty()){
            throw new NoDriverAvailableException();
        }
        return drivers;
    }


    public void addTrip(String driverId, Trip trip){
        if (!exists(driverId)) {
            throw new DriverDoesNotExistException();
        }
        db.get(driverId).getHistory().add(trip);

    }


    /**
     * This method returns if distance of cab < RideServiceApplication.ALLOWED_DISTANCE
     *  Calculating distance can be decided as per Location information
     * @param driver
     * @param location
     * @return
     */
    public boolean isValidDistance(Driver driver, Location location){
        return driver.getLocation().distance(location) < RideServiceApplication.ALLOWED_DISTANCE;
    }

    public void snapshot(){
        System.out.println(db);
    }

}
