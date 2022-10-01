package com.kunal.ride_service.controller;

import com.kunal.ride_service.dao.ScoreDao;
import com.kunal.ride_service.processor.ScoreProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.logging.Logger;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final Logger logger = Logger.getLogger("ScoreController");
    private ScoreDao scoreDao;
    private ScoreProcessor scoreProcessor;


    @PostMapping(value = "/append")
    public ResponseEntity<String> append(@RequestParam HashMap<String, String> params) {
        try {
            scoreProcessor.appendScore(params);
            return ResponseEntity.ok("Player score Successfully added");
        } catch (Exception e) {
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }

    @PostMapping(value = "/getTop5Scores")
    public ResponseEntity<String> getTop5Scores() {
        try {
            logger.info("Get Top 5 Scores API called");
            return ResponseEntity.ok(scoreProcessor.getTop5Scores().toString());
        } catch (Exception e) {
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }

    @PostMapping(value = "/getTop5ScoresFromFile")
    public ResponseEntity<String> getTop5ScoresFromFile() {
        try {
            logger.info("Get Top 5 Scores from file API called");
            return ResponseEntity.ok(scoreProcessor.getTop5ScoresFromFile().toString());
        } catch (Exception e) {
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }

    }
}
