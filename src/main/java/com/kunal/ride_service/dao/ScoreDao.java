package com.kunal.ride_service.dao;

import com.kunal.ride_service.Constants;
import com.kunal.ride_service.database.CacheDatabase;
import com.kunal.ride_service.database.FileDatabase;

import java.io.IOException;

public class ScoreDao {

    private FileDatabase fileDatabase;
    private CacheDatabase cacheDatabase;

    public ScoreDao() throws IOException {
        fileDatabase = new FileDatabase();
        cacheDatabase = new CacheDatabase();
    }

    public void addScore(String str){
        cacheDatabase.append(str);
        if(cacheDatabase.getCacheLength()> Constants.cacheDumpLimit){
            fileDatabase.saveToFile(cacheDatabase.getCache());
            cacheDatabase.clear();
        }
    }


    public void dumpToFileDatabase(){

    }



}
