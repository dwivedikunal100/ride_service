package com.kunal.ride_service.dao;

import com.kunal.ride_service.exceptions.coupon.CouponAlreadyExistsException;
import com.kunal.ride_service.exceptions.coupon.CouponDoesNotExistException;
import com.kunal.ride_service.model.Coupon;

import java.util.HashMap;
import java.util.Map;

public class CouponDao {

     static Map<String, Coupon> db;

    public CouponDao(){
        db = new HashMap<>();
    }

    private boolean exists(String couponId){
        return db.containsKey(couponId);
    }

    public void add(String couponId, Integer discount){
        if(exists(couponId)){
            throw new CouponAlreadyExistsException();
        }
        db.put(couponId,new Coupon(couponId,discount));
    }

    public Coupon apply(String couponId){
        if(!exists(couponId)){
            throw new CouponDoesNotExistException();
        }
        return db.get(couponId);
    }
    public void delete(String couponId){
        if(!exists(couponId)){
            throw new CouponDoesNotExistException();
        }
        db.remove(couponId);
    }

    public void snapshot(){
        System.out.println(db);
    }

}
