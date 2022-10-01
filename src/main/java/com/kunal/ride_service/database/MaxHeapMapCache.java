package com.kunal.ride_service.database;

import com.kunal.ride_service.exceptions.database.CacheMemoryOverflowException;
import com.kunal.ride_service.exceptions.database.CacheMemoryUnderflowException;
import com.kunal.ride_service.model.PlayerScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaxHeapMapCache {

    private static MaxHeapMapCache maxHeapMapCache = null;
    HashMap<String, Integer> positions;
    PlayerScore[] maxHeap;
    int max = 100000;
    int size;

    private MaxHeapMapCache() {
        positions = new HashMap<>();
        maxHeap = new PlayerScore[max];
        size = 0;
    }

    public static MaxHeapMapCache getInstance() {
        if (maxHeapMapCache == null)
            maxHeapMapCache = new MaxHeapMapCache();
        return maxHeapMapCache;
    }

    public void clear() {
        positions.clear();
        size = 0;
    }

    public void addScore(PlayerScore score) {
        if (positions.containsKey(score.getPlayerName())) {
            int curPosition = positions.get(score.getPlayerName());
            PlayerScore newScore = new PlayerScore(score.getPlayerName(), score.getScore() + maxHeap[curPosition].getScore());
            maxHeap[curPosition] = newScore;
            rise(curPosition);
        } else {
            if (size > max) {
                throw new CacheMemoryOverflowException();
            }
            positions.put(score.getPlayerName(), size);
            maxHeap[size] = score;
            rise(size);
            size++;
        }
    }

    public List<PlayerScore> getTopKScores(int K){
        K = Math.min(K,size);
        List<PlayerScore> topKScores = new ArrayList<>();

        //Pop to 5 Scores
        for(int i =0;i<K;i++){
            topKScores.add(pop());
        }

        //Add them back to database
        topKScores.forEach(this::addScore);
        return topKScores;
    }


    private PlayerScore pop(){
        if(size ==0){
            throw new CacheMemoryUnderflowException();
        }
        PlayerScore playerScore = maxHeap[0];
        positions.remove(playerScore.getPlayerName());
        PlayerScore minPlayerScore = maxHeap[size-1];
        positions.put(minPlayerScore.getPlayerName(),0);
        maxHeap[0] = minPlayerScore;
        size--;
        sink(0);
        return playerScore;
    }


    private void rise(int currentPosition) {
        if (currentPosition == 0)
            return;
        int parentPosition = currentPosition >> 1;
        PlayerScore parentScore = maxHeap[parentPosition];
        PlayerScore currentScore = maxHeap[currentPosition];
        if (currentScore.compareTo(parentScore) > 0) {
            maxHeap[currentPosition] = parentScore;
            maxHeap[parentPosition] = currentScore;
            positions.put(currentScore.getPlayerName(), parentPosition);
            positions.put(parentScore.getPlayerName(), currentPosition);
            rise(parentPosition);
        }
    }

    private void sink(int currentPosition){
        //implement yourself
    }



}
