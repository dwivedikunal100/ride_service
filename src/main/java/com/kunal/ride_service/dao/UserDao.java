//package com.kunal.ride_service.dao;
//
//import com.kunal.ride_service.exceptions.driver.DriverDoesNotExistException;
//import com.kunal.ride_service.exceptions.user.UserAlreadyExistsException;
//import com.kunal.ride_service.exceptions.user.UserDoesNotExistException;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class UserDao  {
//
//    static Map<String, User> db;
//
//    public UserDao() {
//        db = new HashMap<>();
//    }
//
//    public void create(String userId, Location location) {
//        if (exists(userId)) {
//            throw new UserAlreadyExistsException();
//        }
//        db.put(userId, new User(userId, location));
//    }
//
//    public void addTrip(String userId, Trip trip){
//        if (!exists(userId)) {
//            throw new UserDoesNotExistException();
//        }
//         db.get(userId).getHistory().add(trip);
//    }
//
//    public boolean exists(String driverId) {
//        return db.containsKey(driverId);
//    }
//
//    public User get(String userId) {
//        if (!exists(userId)) {
//            throw new UserDoesNotExistException();
//        }
//        return db.get(userId);
//    }
//
//    public void update(String userId, Location location){
//        if (!exists(userId)) {
//            throw new DriverDoesNotExistException();
//        }
//        db.get(userId).setLocation(location);
//    }
//
//    public void snapshot(){
//        System.out.println(db);
//    }
//}
