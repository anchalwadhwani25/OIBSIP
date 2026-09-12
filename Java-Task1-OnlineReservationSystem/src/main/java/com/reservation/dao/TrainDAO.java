package com.reservation.dao;

import com.reservation.model.Train;
import com.reservation.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {

    private static final String FIND_BY_NUMBER =
            "SELECT train_id, train_number, train_name, source_station, " +
                    "destination_station, economy_fare, business_fare " +
                    "FROM trains WHERE train_number = ?";

    private static final String FIND_ALL =
            "SELECT train_id, train_number, train_name, source_station, " +
                    "destination_station, economy_fare, business_fare " +
                    "FROM trains ORDER BY train_number";

    public Train findByTrainNumber(int trainNumber) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_NUMBER)) {

            statement.setInt(1, trainNumber);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToTrain(resultSet);
                }
            }

        } catch (SQLException exception) {

            System.err.println("Train search error: "
                    + exception.getMessage());
        }

        return null;
    }

    public List<Train> findAll() {

        List<Train> trains = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                trains.add(mapResultSetToTrain(resultSet));
            }

        } catch (SQLException exception) {

            System.err.println("Train retrieval error: "
                    + exception.getMessage());
        }

        return trains;
    }

    private Train mapResultSetToTrain(ResultSet resultSet)
            throws SQLException {

        return new Train(
                resultSet.getInt("train_id"),
                resultSet.getInt("train_number"),
                resultSet.getString("train_name"),
                resultSet.getString("source_station"),
                resultSet.getString("destination_station"),
                resultSet.getDouble("economy_fare"),
                resultSet.getDouble("business_fare")
        );
    }
}