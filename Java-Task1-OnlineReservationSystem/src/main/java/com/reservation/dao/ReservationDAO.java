package com.reservation.dao;

import com.reservation.model.Reservation;
import com.reservation.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {

    private static final String INSERT_RESERVATION =
            "INSERT INTO reservations " +
                    "(pnr, user_id, train_id, passenger_name, class_type, " +
                    "journey_date, source_station, destination_station) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_PNR =
            "SELECT reservation_id, pnr, user_id, train_id, " +
                    "passenger_name, class_type, journey_date, source_station, " +
                    "destination_station, booking_status " +
                    "FROM reservations WHERE pnr = ?";

    private static final String DELETE_BY_PNR =
            "DELETE FROM reservations WHERE pnr = ?";

    public boolean createReservation(Reservation reservation) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_RESERVATION)) {

            statement.setString(1, reservation.getPnr());
            statement.setInt(2, reservation.getUserId());
            statement.setInt(3, reservation.getTrainId());
            statement.setString(4, reservation.getPassengerName());
            statement.setString(5, reservation.getClassType());
            statement.setDate(
                    6,
                    java.sql.Date.valueOf(reservation.getJourneyDate())
            );
            statement.setString(7, reservation.getSourceStation());
            statement.setString(8, reservation.getDestinationStation());

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {

            System.err.println("Reservation creation error: "
                    + exception.getMessage());

            return false;
        }
    }

    public Reservation findByPNR(String pnr) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_PNR)) {

            statement.setString(1, pnr);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Reservation(
                            resultSet.getInt("reservation_id"),
                            resultSet.getString("pnr"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("train_id"),
                            resultSet.getString("passenger_name"),
                            resultSet.getString("class_type"),
                            resultSet.getDate("journey_date").toLocalDate(),
                            resultSet.getString("source_station"),
                            resultSet.getString("destination_station"),
                            resultSet.getString("booking_status")
                    );
                }
            }

        } catch (SQLException exception) {

            System.err.println("PNR search error: "
                    + exception.getMessage());
        }

        return null;
    }

    public boolean cancelReservation(String pnr) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_BY_PNR)) {

            statement.setString(1, pnr);

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {

            System.err.println("Reservation cancellation error: "
                    + exception.getMessage());

            return false;
        }
    }
}