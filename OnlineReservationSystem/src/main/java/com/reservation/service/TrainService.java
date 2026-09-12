package com.reservation.service;

import com.reservation.dao.TrainDAO;
import com.reservation.model.Train;

import java.util.List;

public class TrainService {

    private final TrainDAO trainDAO;

    public TrainService() {
        this.trainDAO = new TrainDAO();
    }

    public Train findTrainByNumber(String trainNumber) {

        if (trainNumber == null || trainNumber.isBlank()) {
            return null;
        }

        try {
            int number = Integer.parseInt(trainNumber.trim());

            if (number <= 0) {
                return null;
            }

            return trainDAO.findByTrainNumber(number);

        } catch (NumberFormatException exception) {
            return null;
        }
    }

    public List<Train> getAllTrains() {
        return trainDAO.findAll();
    }
}