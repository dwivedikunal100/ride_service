package com.kunal.ride_service.dao;

import com.kunal.ride_service.database.FileDatabase;

import java.io.IOException;

public class ScoreDao {

    private FileDatabase fileDatabase;

    public ScoreDao() throws IOException {
        fileDatabase = new FileDatabase();
    }


}
