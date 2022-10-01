package com.kunal.ride_service.dao;

import com.kunal.ride_service.Constants;
import com.kunal.ride_service.database.CacheDatabase;
import com.kunal.ride_service.database.FileDatabase;
import com.kunal.ride_service.model.PlayerScore;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreDao {

    private final FileDatabase fileDatabase;
    private final CacheDatabase cacheDatabase;


    public ScoreDao() {
        fileDatabase = new FileDatabase();
        cacheDatabase = new CacheDatabase();
    }

    public List<PlayerScore> getPlayerScoresFromFile() {
        return fileDatabase.getAllScores().stream().map(PlayerScore::new).collect(Collectors.toList());
    }

    public void appendScore(PlayerScore score) {
        if (cacheDatabase.getCacheLength() > Constants.cacheDumpLimit) {
            dumpCacheToFile();
        }
        cacheDatabase.append(score);
    }

    public void dumpCacheToFile() {
        fileDatabase.appendScores(cacheDatabase.getCache());
        cacheDatabase.clear();
    }

}
