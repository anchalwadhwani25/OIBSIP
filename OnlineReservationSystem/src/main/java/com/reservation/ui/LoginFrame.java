package com.reservation.ui;

import com.reservation.model.User;
import com.reservation.service.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final AuthService authService;

    public LoginFrame() {

        authService = new AuthService();

        // =========================
        // WINDOW SETTINGS
        // =========================

        setTitle("Online Train Reservation System");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // =========================
        // WELCOME SECTION
        // =========================

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(
                new BoxLayout(welcomePanel, BoxLayout.Y_AXIS)
        );

        welcomePanel.setBackground(
                new Color(35, 75, 120)
        );

        welcomePanel.setBorder(
                new EmptyBorder(25, 20, 25, 20)
        );

        // Train Icon
        JLabel trainIcon = new JLabel(
                "🚆",
                SwingConstants.CENTER
        );

        trainIcon.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 45)
        );

        trainIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        welcomePanel.add(trainIcon);

        // Welcome Text
        JLabel welcomeLabel = new JLabel(
                "WELCOME ABOARD",
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        welcomeLabel.setForeground(Color.WHITE);

        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        welcomePanel.add(
                Box.createVerticalStrut(8)
        );

        welcomePanel.add(welcomeLabel);

        // Subtitle
        JLabel systemLabel = new JLabel(
                "Online Train Reservation System",
                SwingConstants.CENTER
        );

        systemLabel.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        systemLabel.setForeground(
                new Color(225, 235, 245)
        );

        systemLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        welcomePanel.add(
                Box.createVerticalStrut(5)
        );

        welcomePanel.add(systemLabel);

        mainPanel.add(
                welcomePanel,
                BorderLayout.NORTH
        );

        // =========================
        // LOGIN CARD
        // =========================

        JPanel cardPanel = new JPanel(
                new GridBagLayout()
        );

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 224, 230)
                        ),
                        new EmptyBorder(
                                25, 45, 25, 45
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 0, 8, 0);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // =========================
        // LOGIN TITLE
        // =========================

        JLabel loginTitle = new JLabel(
                "LOGIN",
                SwingConstants.CENTER
        );

        loginTitle.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        loginTitle.setForeground(
                new Color(35, 55, 80)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        cardPanel.add(
                loginTitle,
                gbc
        );

        // =========================
        // LOGIN SUBTITLE
        // =========================

        JLabel loginSubtitle = new JLabel(
                "Enter your account details",
                SwingConstants.CENTER
        );

        loginSubtitle.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        loginSubtitle.setForeground(
                new Color(120, 125, 135)
        );

        gbc.gridy = 1;

        cardPanel.add(
                loginSubtitle,
                gbc
        );

        // =========================
        // USERNAME LABEL
        // =========================

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        usernameLabel.setForeground(
                new Color(55, 65, 80)
        );

        gbc.gridy = 2;

        gbc.insets =
                new Insets(25, 0, 5, 0);

        cardPanel.add(
                usernameLabel,
                gbc
        );

        // =========================
        // USERNAME FIELD
        // =========================

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        usernameField.setPreferredSize(
                new Dimension(350, 40)
        );

        usernameField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 195, 205)
                        ),
                        new EmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );

        gbc.gridy = 3;

        gbc.insets =
                new Insets(0, 0, 10, 0);

        cardPanel.add(
                usernameField,
                gbc
        );

        // =========================
        // PASSWORD LABEL
        // =========================

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        passwordLabel.setForeground(
                new Color(55, 65, 80)
        );

        gbc.gridy = 4;

        gbc.insets =
                new Insets(8, 0, 5, 0);

        cardPanel.add(
                passwordLabel,
                gbc
        );

        // =========================
        // PASSWORD FIELD
        // =========================

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        passwordField.setPreferredSize(
                new Dimension(350, 40)
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 195, 205)
                        ),
                        new EmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );

        gbc.gridy = 5;

        gbc.insets =
                new Insets(0, 0, 18, 0);

        cardPanel.add(
                passwordField,
                gbc
        );

        // =========================
        // LOGIN BUTTON
        // =========================

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setBackground(
                new Color(35, 75, 120)
        );

        loginButton.setFocusPainted(false);

        loginButton.setBorderPainted(false);

        loginButton.setPreferredSize(
                new Dimension(350, 42)
        );

        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        gbc.gridy = 6;

        gbc.insets =
                new Insets(5, 0, 18, 0);

        cardPanel.add(
                loginButton,
                gbc
        );

        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel =
                new JLabel(
                        "Secure Train Reservation System",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        footerLabel.setForeground(
                new Color(130, 135, 145)
        );

        gbc.gridy = 7;

        gbc.insets =
                new Insets(0, 0, 0, 0);

        cardPanel.add(
                footerLabel,
                gbc
        );

        // =========================
        // CARD CENTERING
        // =========================

        JPanel centerPanel =
                new JPanel(new GridBagLayout());

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.setBorder(
                new EmptyBorder(
                        25, 40, 25, 40
                )
        );

        centerPanel.add(cardPanel);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // LOGIN ACTION
        // =========================

        loginButton.addActionListener(
                event -> handleLogin()
        );

        // Enter key = Login
        getRootPane().setDefaultButton(
                loginButton
        );

        add(mainPanel);
    }

    // =========================
    // LOGIN FUNCTION
    // =========================

    private void handleLogin() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your username.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your password.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocus();

            return;
        }

        User user =
                authService.login(
                        username,
                        password
                );

        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful. Welcome, "
                            + user.getFullName() + "!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            ReservationFrame reservationFrame =
                    new ReservationFrame(user);

            reservationFrame.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");

            passwordField.requestFocus();
        }
    }
}