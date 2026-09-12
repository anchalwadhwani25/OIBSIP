package com.reservation.model;

public class Train {

    private int trainId;
    private int trainNumber;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
    private double economyFare;
    private double businessFare;

    public Train() {
    }

    public Train(int trainId, int trainNumber, String trainName,
                 String sourceStation, String destinationStation,
                 double economyFare, double businessFare) {

        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.economyFare = economyFare;
        this.businessFare = businessFare;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public double getEconomyFare() {
        return economyFare;
    }

    public void setEconomyFare(double economyFare) {
        this.economyFare = economyFare;
    }

    public double getBusinessFare() {
        return businessFare;
    }

    public void setBusinessFare(double businessFare) {
        this.businessFare = businessFare;
    }

    @Override
    public String toString() {
        return trainNumber + " - " + trainName;
    }
}