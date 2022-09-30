package com.kunal.ride_service.model;

public class PlayerScore {
    String playerName;
    int score;

    public PlayerScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public PlayerScore(String str) {
        String[] list = str.split(",");
        this.playerName = list[0];
        this.score = Integer.parseInt(list[1]);
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public int getScore(){
        return  this.score;
    }


    public String toString(){
        return playerName+","+score;
    }

}
