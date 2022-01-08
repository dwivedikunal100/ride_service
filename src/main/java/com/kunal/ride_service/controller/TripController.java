package com.kunal.ride_service.controller;

import com.kunal.ride_service.Constants;
import com.kunal.ride_service.dao.CouponDao;
import com.kunal.ride_service.dao.DriverDao;
import com.kunal.ride_service.dao.TripDao;
import com.kunal.ride_service.dao.UserDao;
import com.kunal.ride_service.exceptions.coupon.CouponDoesNotExistException;
import com.kunal.ride_service.exceptions.driver.DriverDoesNotExistException;
import com.kunal.ride_service.exceptions.driver.NoDriverAvailableException;
import com.kunal.ride_service.exceptions.trip.TripNotAvailableException;
import com.kunal.ride_service.exceptions.user.UserDoesNotExistException;
import com.kunal.ride_service.model.*;
import com.kunal.ride_service.pricing.DefaultStrategy;
import com.kunal.ride_service.processor.TripProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
public class TripController {

    private DriverDao driverDao;
    private UserDao userDao;
    private CouponDao couponDao;
    private TripDao tripDao;

    public TripController() {
        couponDao = new CouponDao();
        userDao = new UserDao();
        driverDao = new DriverDao();
        tripDao = new TripDao();
    }

    @PostMapping(value = "/find")
    public ResponseEntity<String> findRides(@RequestParam HashMap<String, String> params) {
        try {
            User user = userDao.get(params.get(Constants.userId));
            List<Driver> availableDrivers = driverDao.getDriversNearLocation(user.getLocation());
            HashMap<String, Double> pricesWithDrivers = TripProcessor.getDriversWithPrices(availableDrivers, user.getLocation(), new Location(params.get(Constants.x_coordinate), params.get(Constants.y_coordinate)));
            return ResponseEntity.ok(pricesWithDrivers.toString());
        } catch (UserDoesNotExistException userDoesNotExistException) {
            return ResponseEntity.ok("User does not exists");
        } catch (NoDriverAvailableException noDriverAvailableException) {
            return ResponseEntity.ok("No drivers nearby");
        }
    }


    @PostMapping(value = "/start")
    public ResponseEntity<String> startRide(@RequestParam final HashMap<String, String> params) {
        try {
            String userId = params.get(Constants.userId);
            String driverId = params.get(Constants.driverId);
            final User user = userDao.get(userId);
            final Driver driver = driverDao.get(driverId);
            if (!driver.isAvailable() || !driverDao.isValidDistance(driver, user.getLocation())) {
                return ResponseEntity.ok("Driver is not available or is not near by");
            }
            Optional<Coupon> coupon = Optional.empty();
            if (params.containsKey(Constants.couponId)) {
                coupon = Optional.of(couponDao.apply(params.get(Constants.couponId)));
            }
            Location destination = new Location(params.get(Constants.x_coordinate), params.get(Constants.y_coordinate));
            double finalPrice = new DefaultStrategy().price(driver.getCabType(), user.getLocation(), destination, coupon);
            Trip trip = tripDao.createTrip(userId, driverId, user.getLocation(), destination, finalPrice);
            userDao.addTrip(userId, trip);
            driverDao.setAvailability(driverId, false);
            driverDao.addTrip(driverId, trip);
            return ResponseEntity.ok("Ride Started Successfully with Trip Id " + trip.getId());
        } catch (UserDoesNotExistException userDoesNotExistException) {
            return ResponseEntity.ok("User does not exist");
        } catch (DriverDoesNotExistException driverDoesNotExistException) {
            return ResponseEntity.ok("Driver does not exists");
        } catch (CouponDoesNotExistException couponDoesNotExistException) {
            return ResponseEntity.ok("Coupon does not exists");
        } catch (Exception exception) {
            return ResponseEntity.ok("Ride could not start");
        }
    }


    @PostMapping(value = "/end")
    public ResponseEntity<String> endTrip(@RequestParam final HashMap<String, String> params) {
        try {
            final String userId = params.get(Constants.userId);
            final String driverId = params.get(Constants.driverId);
            final Integer tripId = Integer.parseInt(params.get(Constants.tripId));
            tripDao.endTrip(tripId);
            final Trip trip = tripDao.get(tripId);
            // When trip has ended set driver as free and update its location
            driverDao.setAvailability(driverId, true);
            // Updating location of both user and driver
            driverDao.update(trip.getDriverId(), trip.getDestination());
            userDao.update(userId, trip.getDestination());
            return ResponseEntity.ok("Trip ended successfully");
        } catch (TripNotAvailableException tripNotAvailableException) {
            return ResponseEntity.ok("Trip does not exists");
        } catch (UserDoesNotExistException userDoesNotExistException) {
            return ResponseEntity.ok("User does not exists");
        } catch (DriverDoesNotExistException driverDoesNotExistException) {
            return ResponseEntity.ok("Driver does not exists");
        }
    }

}
