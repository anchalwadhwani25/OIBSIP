package com.reservation.ui;

import com.reservation.model.Reservation;
import com.reservation.service.ReservationService;

import javax.swing.*;
import java.awt.*;

public class CancellationFrame extends JFrame {

    private final ReservationService reservationService;

    private JTextField pnrField;
    private JTextArea reservationDetailsArea;

    private JButton searchButton;
    private JButton cancelButton;

    private Reservation currentReservation;

    public CancellationFrame() {

        reservationService = new ReservationService();

        initializeFrame();
        createUI();
    }

    private void initializeFrame() {

        setTitle("Reservation Cancellation");

        setSize(500, 400);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel titleLabel = new JLabel(
                "CANCEL RESERVATION",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JPanel searchPanel =
                new JPanel(new BorderLayout(10, 10));

        JLabel pnrLabel =
                new JLabel("Enter PNR:");

        pnrField = new JTextField();

        searchButton =
                new JButton("Search");

        searchPanel.add(
                pnrLabel,
                BorderLayout.WEST
        );

        searchPanel.add(
                pnrField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                searchButton,
                BorderLayout.EAST
        );

        mainPanel.add(
                searchPanel,
                BorderLayout.CENTER
        );

        reservationDetailsArea =
                new JTextArea();

        reservationDetailsArea.setEditable(false);

        reservationDetailsArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        reservationDetailsArea.setBorder(
                BorderFactory.createTitledBorder(
                        "Reservation Details"
                )
        );

        mainPanel.add(
                new JScrollPane(
                        reservationDetailsArea
                ),
                BorderLayout.SOUTH
        );

        cancelButton =
                new JButton("Confirm Cancellation");

        cancelButton.setEnabled(false);

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        buttonPanel.add(cancelButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.PAGE_END
        );

        add(mainPanel);

        searchButton.addActionListener(
                event -> searchReservation()
        );

        cancelButton.addActionListener(
                event -> confirmCancellation()
        );

        pnrField.addActionListener(
                event -> searchReservation()
        );
    }

    private void searchReservation() {

        String pnr =
                pnrField.getText()
                        .trim()
                        .toUpperCase();

        if (pnr.isEmpty()) {

            showWarning(
                    "Please enter a PNR."
            );

            pnrField.requestFocus();

            return;
        }

        currentReservation =
                reservationService
                        .findReservationByPNR(pnr);

        if (currentReservation == null) {

            reservationDetailsArea.setText("");

            cancelButton.setEnabled(false);

            showError(
                    "No reservation found for PNR: "
                            + pnr
            );

            return;
        }

        displayReservation(
                currentReservation
        );

        cancelButton.setEnabled(true);
    }

    private void displayReservation(
            Reservation reservation
    ) {

        String details =
                "PNR              : "
                        + reservation.getPnr()
                        + "\n"
                        + "Passenger Name    : "
                        + reservation.getPassengerName()
                        + "\n"
                        + "Class             : "
                        + reservation.getClassType()
                        + "\n"
                        + "Journey Date      : "
                        + reservation.getJourneyDate()
                        + "\n"
                        + "Source            : "
                        + reservation.getSourceStation()
                        + "\n"
                        + "Destination       : "
                        + reservation.getDestinationStation()
                        + "\n"
                        + "Booking Status    : "
                        + reservation.getBookingStatus();

        reservationDetailsArea.setText(details);
    }

    private void confirmCancellation() {

        if (currentReservation == null) {
            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel "
                                + "reservation "
                                + currentReservation.getPnr()
                                + "?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        boolean cancelled =
                reservationService
                        .cancelReservation(
                                currentReservation.getPnr()
                        );

        if (cancelled) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation cancelled successfully.",
                    "Cancellation Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            currentReservation = null;

            reservationDetailsArea.setText("");

            pnrField.setText("");

            cancelButton.setEnabled(false);

        } else {

            showError(
                    "Reservation could not be cancelled."
            );
        }
    }

    private void showWarning(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}