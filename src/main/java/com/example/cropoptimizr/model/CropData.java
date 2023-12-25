package com.example.cropoptimizr.model;

public class CropData {
    private double headsPer;
    private double grainCount;
    private double grainWeight;

    public CropData(double headsPer, double grainCount, double grainWeight) {
        this.headsPer = headsPer;
        this.grainCount = grainCount;
        this.grainWeight = grainWeight;
    }

    public double getHeadsPer(){
        return headsPer;
    }
    public double getGrainCount() {
        return grainCount;
    }

    public double getGrainWeight() {
        return grainWeight;
    }
}