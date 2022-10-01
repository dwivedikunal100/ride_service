package com.kunal.ride_service.processor;

import com.kunal.ride_service.dao.ScoreDao;
import com.kunal.ride_service.database.MaxHeapMapCache;
import com.kunal.ride_service.model.PlayerScore;

import java.util.Collections;
import java.util.List;

public class ScoreProcessor {

    private ScoreDao scoreDao;
    MaxHeapMapCache maxHeapMapCache;

    public List<String> getTop5Scores() {
        List<PlayerScore> scoreProcessorsList = scoreDao.getPlayerScoresFromFile();
        maxHeapMapCache = MaxHeapMapCache.getInstance();
        scoreProcessorsList.forEach(score -> maxHeapMapCache.addScore(score));
        //getTop5
        return Collections.singletonList("empty");
    }

    public List<String> getTop5ScoresFromFile() {
        maxHeapMapCache = MaxHeapMapCache.getInstance();
        maxHeapMapCache.clear();
        List<PlayerScore> scoreProcessorsList = scoreDao.getPlayerScoresFromFile();
        scoreProcessorsList.forEach(score -> maxHeapMapCache.addScore(score));
        //getTop5
        return Collections.singletonList("empty");
    }
}
