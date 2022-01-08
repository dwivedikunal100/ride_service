package com.kunal.ride_service.controller;

import com.kunal.ride_service.dao.DriverDao;
import com.kunal.ride_service.exceptions.driver.DriverAlreadyExistsException;
import com.kunal.ride_service.exceptions.driver.DriverDoesNotExistException;
import com.kunal.ride_service.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import  com.kunal.ride_service.Constants;


@RestController
@RequestMapping("/driver")
public class DriverController {


    private DriverDao driverDao;

    public DriverController() {
        driverDao = new DriverDao();
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestParam HashMap<String,String> params) {
        try {
            driverDao.create(params.get(Constants.driverId),
                    new Location(params.get(Constants.x_coordinate), params.get(Constants.y_coordinate)), CabType.valueOf(params.get(Constants.cabType)));
            return ResponseEntity.ok("Driver successfully created");
        } catch (DriverAlreadyExistsException driverAlreadyExistsException) {
            return ResponseEntity.ok("Driver already exists");
        }
    }

    @PostMapping(value = "/location")
    public ResponseEntity updateCabLocation(@RequestParam HashMap<String,String> params) {
        try {
            driverDao.update(params.get(Constants.driverId), new Location(params.get(Constants.x_coordinate), params.get(Constants.y_coordinate)));
            return ResponseEntity.ok("Driver location uploaded successfully");
        } catch (DriverDoesNotExistException driverDoesNotExistException) {
            return ResponseEntity.ok("Driver does not exists");
        }
    }

    @PostMapping(value = "/history")
    public ResponseEntity<String> history(@RequestParam HashMap<String,String> params) {
        try {
            Driver driver = driverDao.get(params.get(Constants.driverId));
            List<Trip> tripsHistory = driver.getHistory();
            return ResponseEntity.ok(tripsHistory.isEmpty()? "No History available":tripsHistory.toString());
        } catch (DriverDoesNotExistException driverDoesNotExistException) {
            return ResponseEntity.ok("Driver does not exists");
        }
    }

}
