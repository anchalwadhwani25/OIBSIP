package com.reservation.service;

import com.reservation.dao.ReservationDAO;
import com.reservation.model.Reservation;
import com.reservation.model.Train;

import java.time.LocalDate;

public class ReservationService {

    private final ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public boolean createReservation(
            int userId,
            Train train,
            String passengerName,
            String classType,
            LocalDate journeyDate,
            String pnr
    ) {

        if (userId <= 0 || train == null) {
            return false;
        }

        if (passengerName == null || passengerName.isBlank()) {
            return false;
        }

        if (classType == null ||
                (!classType.equals("Economy")
                        && !classType.equals("Business"))) {
            return false;
        }

        if (journeyDate == null ||
                journeyDate.isBefore(LocalDate.now())) {
            return false;
        }

        if (pnr == null || pnr.isBlank()) {
            return false;
        }

        Reservation reservation = new Reservation(
                0,
                pnr,
                userId,
                train.getTrainId(),
                passengerName.trim(),
                classType,
                journeyDate,
                train.getSourceStation(),
                train.getDestinationStation(),
                "CONFIRMED"
        );

        return reservationDAO.createReservation(reservation);
    }

    public Reservation findReservationByPNR(String pnr) {

        if (pnr == null || pnr.isBlank()) {
            return null;
        }

        return reservationDAO.findByPNR(
                pnr.trim().toUpperCase()
        );
    }

    public boolean cancelReservation(String pnr) {

        if (pnr == null || pnr.isBlank()) {
            return false;
        }

        return reservationDAO.cancelReservation(
                pnr.trim().toUpperCase()
        );
    }
}