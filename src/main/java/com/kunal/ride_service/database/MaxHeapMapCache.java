package com.kunal.ride_service.database;

import com.kunal.ride_service.exceptions.database.CacheMemoryOverflowException;
import com.kunal.ride_service.model.PlayerScore;

import java.util.HashMap;

public class MaxHeapMapCache {

    private static MaxHeapMapCache maxHeapMapCache = null;
    HashMap<String, Integer> positions;
    PlayerScore maxHeap[];
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

    private void rise(int currentPosition) {
        if (currentPosition == 0)
            return;
        int parentPosition = currentPosition >> 1;
        PlayerScore parentScore = maxHeap[parentPosition];
        PlayerScore currentScore = maxHeap[currentPosition];
        if (parentScore.getScore() < currentScore.getScore()) {
            maxHeap[currentPosition] = parentScore;
            maxHeap[parentPosition] = currentScore;
            positions.put(currentScore.getPlayerName(),parentPosition);
            positions.put(parentScore.getPlayerName(),currentPosition);
            rise(parentPosition);
        }
    }



}
