package com.kunal.ride_service.database;

import com.kunal.ride_service.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class FileDatabase {

    private BufferedReader bufferedReader;

    private final Logger logger = Logger.getLogger("FileDatabase");

    public FileDatabase() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(Constants.fileName))));
    }


    public List<String> getAllScores() {
        try {
            List<String> scores = new ArrayList<>();
            String str;
            while ((str=bufferedReader.readLine())!=null){
                scores.add(str);
            }
            return scores;
        } catch (Exception e) {
            logger.severe("Error while reading file");
        }
        return Collections.emptyList();
    }


}
