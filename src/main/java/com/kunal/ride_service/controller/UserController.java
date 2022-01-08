package com.kunal.ride_service.controller;

import com.kunal.ride_service.Constants;

import com.kunal.ride_service.dao.UserDao;
import com.kunal.ride_service.exceptions.driver.NoDriverAvailableException;
import com.kunal.ride_service.exceptions.user.UserAlreadyExistsException;

import com.kunal.ride_service.exceptions.user.UserDoesNotExistException;
import com.kunal.ride_service.model.Location;


import com.kunal.ride_service.model.Trip;
import com.kunal.ride_service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestParam HashMap<String, String> params) {
        try {
            userDao.create(params.get(Constants.userId),
                    new Location(params.get(Constants.x_coordinate), params.get(Constants.y_coordinate)));
            return ResponseEntity.ok("User Successfully created");
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            return ResponseEntity.ok("User already  exists");
        }
    }


    @PostMapping(value = "/history")
    public ResponseEntity<String> history(@RequestParam HashMap<String, String> params) {
        try {
            User user = userDao.get(params.get(Constants.userId));
            List<Trip> tripsHistory = user.getHistory();
             return ResponseEntity.ok(tripsHistory.isEmpty()? "No History available":tripsHistory.toString());
        } catch (UserDoesNotExistException userDoesNotExistException) {
            return ResponseEntity.ok("User does not exists");
        }
    }
}

