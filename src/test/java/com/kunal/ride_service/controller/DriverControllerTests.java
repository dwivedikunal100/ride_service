package com.kunal.ride_service.controller;

import com.kunal.ride_service.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class DriverControllerTests {

    private DriverController driverController;
    static HashMap<String,String> driverParams;

    public DriverControllerTests(){
        driverController = new DriverController();
        driverParams = new HashMap<>();
        driverParams.put(Constants.driverId,"A");
        driverParams.put(Constants.x_coordinate,"0");
        driverParams.put(Constants.y_coordinate,"0");
        driverParams.put(Constants.cabType,"Sedan");
    }

    @Test
    public void driverSuccessfullyCreatedTest(){
        assert(driverController.create(driverParams).getBody().equals("Driver successfully created"));
    }

    @Test
    public void userDuplicateCreatedTest(){
        driverController.create(driverParams);
        assert(driverController.create(driverParams).getBody().equals("Driver already exists"));
    }

    @Test
    public void userDoesNotExistsTest(){
        assert(driverController.history(driverParams).getBody().equals("Driver does not exists"));
    }

}
