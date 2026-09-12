package com.reservation.model;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private String pnr;
    private int userId;
    private int trainId;
    private String passengerName;
    private String classType;
    private LocalDate journeyDate;
    private String sourceStation;
    private String destinationStation;
    private String bookingStatus;

    public Reservation() {
    }

    public Reservation(int reservationId, String pnr, int userId, int trainId,
                       String passengerName, String classType,
                       LocalDate journeyDate, String sourceStation,
                       String destinationStation, String bookingStatus) {

        this.reservationId = reservationId;
        this.pnr = pnr;
        this.userId = userId;
        this.trainId = trainId;
        this.passengerName = passengerName;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.bookingStatus = bookingStatus;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", pnr='" + pnr + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", classType='" + classType + '\'' +
                ", journeyDate=" + journeyDate +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }
}