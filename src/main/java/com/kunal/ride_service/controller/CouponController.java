package com.kunal.ride_service.controller;

import com.kunal.ride_service.dao.CouponDao;
import com.kunal.ride_service.exceptions.coupon.CouponAlreadyExistsException;
import com.kunal.ride_service.exceptions.coupon.CouponDoesNotExistException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import  com.kunal.ride_service.Constants;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    private CouponDao couponDao;


    public CouponController() {
        couponDao = new CouponDao();
    }

    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestParam HashMap<String,String> params) {
        try {
            couponDao.add(params.get(Constants.couponId), Integer.parseInt(params.get(Constants.discount)));
            return ResponseEntity.ok("Coupon successfully created");
        } catch (CouponAlreadyExistsException couponAlreadyExistsException) {
            return ResponseEntity.ok("Coupon already exists");
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam HashMap<String,String> params) {
        try {
            couponDao.delete(params.get(Constants.couponId));
            return ResponseEntity.ok("Coupon deleted successfully");
        } catch (CouponDoesNotExistException couponDoesNotExistException) {
            return ResponseEntity.ok("Coupon does not exist");
        }
    }


}
