package com.reservation.ui;

import com.reservation.model.Train;
import com.reservation.model.User;
import com.reservation.service.ReservationService;
import com.reservation.service.TrainService;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class ReservationFrame extends JFrame {

    private final User loggedInUser;
    private final TrainService trainService;
    private final ReservationService reservationService;

    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JTextField passengerNameField;
    private JTextField journeyDateField;
    private JTextField sourceField;
    private JTextField destinationField;

    private JComboBox<String> classComboBox;

    private JButton searchTrainButton;
    private JButton bookButton;
    private JButton cancelButton;

    private Train selectedTrain;

    public ReservationFrame(User user) {

        loggedInUser = user;
        trainService = new TrainService();
        reservationService = new ReservationService();

        initializeFrame();
        createUI();
    }

    private void initializeFrame() {

        setTitle("Online Train Reservation System - Reservation");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 25, 40
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "TRAIN RESERVATION",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(
                new Color(35, 55, 80)
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 224, 230)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 25, 20, 25
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Train Number
        JLabel trainNumberLabel =
                new JLabel("Train Number:");

        trainNumberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        trainNumberField =
                new JTextField();

        searchTrainButton =
                new JButton("Search Train");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;

        formPanel.add(
                trainNumberLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                trainNumberField,
                gbc
        );

        gbc.gridx = 2;
        gbc.weightx = 0;

        formPanel.add(
                searchTrainButton,
                gbc
        );

        // Train Name
        JLabel trainNameLabel =
                new JLabel("Train Name:");

        trainNameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        trainNameField =
                new JTextField();

        trainNameField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                trainNameLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;

        formPanel.add(
                trainNameField,
                gbc
        );

        gbc.gridwidth = 1;

        // Passenger Name
        JLabel passengerNameLabel =
                new JLabel("Passenger Name:");

        passengerNameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        passengerNameField =
                new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                passengerNameLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                passengerNameField,
                gbc
        );

        gbc.gridwidth = 1;

        // Class
        JLabel classLabel =
                new JLabel("Class:");

        classLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        classComboBox =
                new JComboBox<>(
                        new String[]{
                                "Economy",
                                "Business"
                        }
                );

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                classLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                classComboBox,
                gbc
        );

        gbc.gridwidth = 1;

        // Journey Date
        JLabel journeyDateLabel =
                new JLabel("Journey Date:");

        journeyDateLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        journeyDateField =
                new JTextField();

        journeyDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridx = 0;
        gbc.gridy = 4;

        formPanel.add(
                journeyDateLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                journeyDateField,
                gbc
        );

        gbc.gridwidth = 1;

        // Source
        JLabel sourceLabel =
                new JLabel("Source:");

        sourceLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        sourceField =
                new JTextField();

        sourceField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 5;

        formPanel.add(
                sourceLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                sourceField,
                gbc
        );

        gbc.gridwidth = 1;

        // Destination
        JLabel destinationLabel =
                new JLabel("Destination:");

        destinationLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        destinationField =
                new JTextField();

        destinationField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 6;

        formPanel.add(
                destinationLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                destinationField,
                gbc
        );

        gbc.gridwidth = 1;

        // Logged-in User
        JLabel userLabel =
                new JLabel("Logged-in User:");

        userLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JTextField userField =
                new JTextField(
                        loggedInUser.getFullName()
                );

        userField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 7;

        formPanel.add(
                userLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        formPanel.add(
                userField,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5
                        )
                );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        bookButton =
                new JButton("Book Reservation");

        cancelButton =
                new JButton("Cancel Reservation");

        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        searchTrainButton.addActionListener(
                event -> searchTrain()
        );

        bookButton.addActionListener(
                event -> bookReservation()
        );

        cancelButton.addActionListener(
                event -> openCancellationWindow()
        );

        trainNumberField.addActionListener(
                event -> searchTrain()
        );
    }

    private void searchTrain() {

        String trainNumber =
                trainNumberField
                        .getText()
                        .trim();

        if (trainNumber.isEmpty()) {

            showWarning(
                    "Please enter a train number."
            );

            trainNumberField.requestFocus();

            return;
        }

        if (!trainNumber.matches("\\d+")) {

            showWarning(
                    "Train number must contain digits only."
            );

            trainNumberField.requestFocus();

            return;
        }

        Train train =
                trainService.findTrainByNumber(
                        trainNumber
                );

        if (train == null) {

            showWarning(
                    "Invalid Train Number!\n\n"
                            + "Available train numbers are:\n"
                            + "201, 202, 203, 204, 205, 206."
            );

            clearTrainDetails();

            trainNumberField.requestFocus();

            return;
        }

        selectedTrain = train;

        trainNameField.setText(
                train.getTrainName()
        );

        sourceField.setText(
                train.getSourceStation()
        );

        destinationField.setText(
                train.getDestinationStation()
        );
    }

    private void bookReservation() {

        if (selectedTrain == null) {

            showWarning(
                    "Please search for a valid train first."
            );

            return;
        }

        String passengerName =
                passengerNameField
                        .getText()
                        .trim();

        if (passengerName.isEmpty()) {

            showWarning(
                    "Please enter passenger name."
            );

            passengerNameField.requestFocus();

            return;
        }

        String dateText =
                journeyDateField
                        .getText()
                        .trim();

        if (dateText.isEmpty()) {

            showWarning(
                    "Please enter journey date.\n"
                            + "Format: YYYY-MM-DD"
            );

            journeyDateField.requestFocus();

            return;
        }

        LocalDate journeyDate;

        try {

            journeyDate =
                    LocalDate.parse(dateText);

        } catch (DateTimeParseException exception) {

            showError(
                    "Invalid date format.\n"
                            + "Please use YYYY-MM-DD."
            );

            journeyDateField.requestFocus();

            return;
        }

        if (journeyDate.isBefore(
                LocalDate.now()
        )) {

            showWarning(
                    "Journey date cannot be in the past."
            );

            journeyDateField.requestFocus();

            return;
        }

        String classType =
                (String) classComboBox
                        .getSelectedItem();

        String pnr =
                generatePNR();

        boolean successful =
                reservationService.createReservation(
                        loggedInUser.getUserId(),
                        selectedTrain,
                        passengerName,
                        classType,
                        journeyDate,
                        pnr
                );

        if (successful) {

            showReservationSuccess(
                    pnr,
                    passengerName,
                    classType,
                    journeyDate
            );

            clearForm();

        } else {

            showError(
                    "Reservation could not be created."
            );
        }
    }

    private String generatePNR() {

        return "PNR"
                + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }

    private void showReservationSuccess(
            String pnr,
            String passengerName,
            String classType,
            LocalDate journeyDate
    ) {

        JDialog dialog =
                new JDialog(
                        this,
                        "Reservation Successful",
                        true
                );

        dialog.setSize(500, 430);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "RESERVATION SUCCESSFUL",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(10, 15)
                );

        topPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JPanel pnrPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        JLabel pnrLabel =
                new JLabel("PNR:");

        pnrLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        JTextField pnrField =
                new JTextField(pnr);

        pnrField.setEditable(false);

        pnrField.setHorizontalAlignment(
                JTextField.CENTER
        );

        pnrField.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        JButton copyButton =
                new JButton("Copy PNR");

        copyButton.addActionListener(
                event -> copyPNR(
                        pnr,
                        dialog
                )
        );

        pnrPanel.add(
                pnrLabel,
                BorderLayout.WEST
        );

        pnrPanel.add(
                pnrField,
                BorderLayout.CENTER
        );

        pnrPanel.add(
                copyButton,
                BorderLayout.EAST
        );

        topPanel.add(
                pnrPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        JTextArea detailsArea =
                new JTextArea();

        detailsArea.setEditable(false);

        detailsArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        detailsArea.setText(
                "Passenger Name : "
                        + passengerName
                        + "\n"
                        + "Train          : "
                        + selectedTrain.getTrainName()
                        + "\n"
                        + "Train Number   : "
                        + selectedTrain.getTrainNumber()
                        + "\n"
                        + "Class          : "
                        + classType
                        + "\n"
                        + "Journey Date   : "
                        + journeyDate
                        + "\n"
                        + "Source         : "
                        + selectedTrain.getSourceStation()
                        + "\n"
                        + "Destination    : "
                        + selectedTrain.getDestinationStation()
                        + "\n\n"
                        + "Please save your PNR for cancellation."
        );

        detailsArea.setBorder(
                BorderFactory.createTitledBorder(
                        "Reservation Details"
                )
        );

        mainPanel.add(
                new JScrollPane(detailsArea),
                BorderLayout.CENTER
        );

        JButton doneButton =
                new JButton("Done");

        doneButton.addActionListener(
                event -> dialog.dispose()
        );

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(doneButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        dialog.add(mainPanel);

        dialog.setVisible(true);
    }

    private void copyPNR(
            String pnr,
            JDialog dialog
    ) {

        StringSelection selection =
                new StringSelection(pnr);

        Clipboard clipboard =
                Toolkit
                        .getDefaultToolkit()
                        .getSystemClipboard();

        clipboard.setContents(
                selection,
                null
        );

        JOptionPane.showMessageDialog(
                dialog,
                "PNR copied successfully!",
                "Copied",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void openCancellationWindow() {

        CancellationFrame cancellationFrame =
                new CancellationFrame();

        cancellationFrame.setVisible(true);
    }

    private void clearTrainDetails() {

        selectedTrain = null;

        trainNameField.setText("");
        sourceField.setText("");
        destinationField.setText("");
    }

    private void clearForm() {

        trainNumberField.setText("");
        trainNameField.setText("");
        passengerNameField.setText("");
        journeyDateField.setText("");
        sourceField.setText("");
        destinationField.setText("");

        classComboBox.setSelectedIndex(0);

        selectedTrain = null;

        trainNumberField.requestFocus();
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