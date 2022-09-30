package com.kunal.ride_service.database;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileDatabase {

    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;


    public void getAllScores(final List<String> list) {
        try {
            inputStreamReader.close();
        } catch (Exception e) {

        }
    }


}
