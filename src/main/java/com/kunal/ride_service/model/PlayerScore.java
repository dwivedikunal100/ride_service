package com.kunal.ride_service.model;

import java.time.LocalDateTime;

public class PlayerScore {
    String timestamp;
    String playerName;
    int score;

    public PlayerScore(String playerName, int score) {
        this.timestamp = LocalDateTime.now().toString();
        this.playerName = playerName;
        this.score = score;
    }

    public PlayerScore(String timestamp, String playerName, int score) {
        this.timestamp = timestamp;
        this.playerName = playerName;
        this.score = score;
    }
}
