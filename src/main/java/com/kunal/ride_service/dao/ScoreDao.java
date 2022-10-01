package com.kunal.ride_service.dao;

import com.kunal.ride_service.database.FileDatabase;
import com.kunal.ride_service.model.PlayerScore;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreDao {

    private FileDatabase fileDatabase;

    public ScoreDao() throws IOException {
        fileDatabase = new FileDatabase();
    }

    public List<PlayerScore> getPlayerScoresFromFile() {
        return fileDatabase.getAllScores().stream().map(PlayerScore::new).collect(Collectors.toList());
    }


}
