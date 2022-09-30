package com.kunal.ride_service.controller;

import com.kunal.ride_service.dao.ScoreDao;
import com.kunal.ride_service.exceptions.score.PlayerAlreadyExistsException;
import com.kunal.ride_service.processor.ScoreProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private ScoreDao scoreDao;
    private ScoreProcessor scoreProcessor;

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(@RequestParam HashMap<String, String> params) {
        try {

            return ResponseEntity.ok("Player score Successfully added");
        } catch (PlayerAlreadyExistsException playerAlreadyExistsException) {
            return ResponseEntity.ok("Player already  exists");
        }
    }

    @PostMapping(value = "/getTop5Scores")
    public ResponseEntity<String> getTop5Scores() {
        try {
            return ResponseEntity.ok("Player score Successfully added");
        } catch (PlayerAlreadyExistsException playerAlreadyExistsException) {
            return ResponseEntity.ok("Player already  exists");
        }
    }
}
