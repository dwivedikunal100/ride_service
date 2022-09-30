package com.kunal.ride_service.controller;

import com.kunal.ride_service.dao.ScoreDao;
import com.kunal.ride_service.exceptions.score.PlayerAlreadyExistsException;
import com.kunal.ride_service.processor.ScoreProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private ScoreDao scoreDao;
    private ScoreProcessor scoreProcessor;

    @PostMapping(value = "/getTop5Scores")
    public ResponseEntity<String> getTop5Scores() {
        try {
            return ResponseEntity.ok(scoreProcessor.getTop5Scores().toString());
        } catch (PlayerAlreadyExistsException playerAlreadyExistsException) {
            return ResponseEntity.ok("Player already  exists");
        }
    }
}
