package com.kunal.ride_service.model;

public enum CabType {
    Sedan(15),
    Hatchback(12);

    private int rate;

    public int getRate() {
        return this.rate;
    }

    CabType(int rate) {
        this.rate = rate;
    }

}
