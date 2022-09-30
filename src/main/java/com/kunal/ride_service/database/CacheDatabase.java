package com.kunal.ride_service.database;

import java.util.ArrayList;
import java.util.List;

public class CacheDatabase {

    private List<String> cache;

    public CacheDatabase(){
        cache = new ArrayList<>();
    }

    public void clear(){
        cache.clear();
    }

    public void append(String str){
        cache.add(str);
    }

    public List<String> getCache(){
        return cache;
    }

    public int getCacheLength(){
        return cache.size();
    }


}
