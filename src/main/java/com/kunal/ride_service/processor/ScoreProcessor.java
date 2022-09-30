package com.kunal.ride_service.processor;

import com.kunal.ride_service.dao.ScoreDao;
import com.kunal.ride_service.model.PlayerScore;

import java.util.Collections;
import java.util.List;

public class ScoreProcessor {

    private ScoreDao scoreDao;
    public List<String> getTop5Scores() {
        List<PlayerScore> scoreProcessorsList = scoreDao.getPlayerScores();
        return Collections.singletonList("empty");
    }
}
