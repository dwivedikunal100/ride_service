package com.kunal.ride_service.processor;

import com.kunal.ride_service.Constants;
import com.kunal.ride_service.dao.ScoreDao;
import com.kunal.ride_service.database.MaxHeapMapCache;
import com.kunal.ride_service.model.PlayerScore;

import java.util.HashMap;
import java.util.List;

public class ScoreProcessor {

    MaxHeapMapCache maxHeapMapCache;
    private ScoreDao scoreDao;


    public void appendScore(HashMap<String, String> params) {
        scoreDao.appendScore(new PlayerScore(params.get(Constants.playerName), Integer.parseInt(params.get(Constants.score))));
    }

    public List<PlayerScore> getTop5Scores() {
        List<PlayerScore> scoreProcessorsList = scoreDao.getPlayerScoresFromFile();
        maxHeapMapCache = MaxHeapMapCache.getInstance();
        scoreProcessorsList.forEach(score -> maxHeapMapCache.addScore(score));
        //getTop5
        return maxHeapMapCache.getTopKScores(5);
    }

    public List<PlayerScore> getTop5ScoresFromFile() {
        maxHeapMapCache = MaxHeapMapCache.getInstance();
        maxHeapMapCache.clear();
        scoreDao.dumpCacheToFile();
        List<PlayerScore> scoreProcessorsList = scoreDao.getPlayerScoresFromFile();
        scoreProcessorsList.forEach(score -> maxHeapMapCache.addScore(score));
        //getTop5
        return maxHeapMapCache.getTopKScores(5);
    }
}
