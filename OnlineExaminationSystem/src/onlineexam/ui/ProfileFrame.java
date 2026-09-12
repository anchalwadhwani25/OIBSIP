package onlineexam.ui;

import onlineexam.dao.ExamDAO;
import onlineexam.model.User;

import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends JFrame {

    private User user;
    private ExamDAO examDAO;

    private JTextField fullNameField;
    private JPasswordField passwordField;

    public ProfileFrame(User user) {

        this.user = user;
        this.examDAO = new ExamDAO();

        setTitle("Student Profile");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Student Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(165, 25, 200, 30);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 90, 120, 25);
        panel.add(usernameLabel);

        JLabel usernameValue = new JLabel(user.getUsername());
        usernameValue.setBounds(200, 90, 200, 25);
        panel.add(usernameValue);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(70, 135, 120, 25);
        panel.add(nameLabel);

        fullNameField = new JTextField(user.getFullName());
        fullNameField.setBounds(200, 135, 220, 25);
        panel.add(fullNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 180, 120, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(user.getPassword());
        passwordField.setBounds(200, 180, 220, 25);
        panel.add(passwordField);

        JButton updateButton = new JButton("Update Profile");
        updateButton.setBounds(160, 225, 170, 35);
        panel.add(updateButton);

        JButton startExamButton = new JButton("Start Exam");
        startExamButton.setBounds(160, 275, 170, 35);
        panel.add(startExamButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(160, 325, 170, 35);
        panel.add(logoutButton);

        updateButton.addActionListener(e -> updateProfile());

        startExamButton.addActionListener(e -> {
            dispose();
            new ExamFrame(user).setVisible(true);
        });

        logoutButton.addActionListener(e -> logout());

        add(panel);
    }

    private void updateProfile() {

        String fullName = fullNameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (fullName.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Full Name and Password cannot be empty.",
                    "Update Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean updated = examDAO.updateProfile(
                user.getUserId(),
                fullName,
                password
        );

        if (updated) {

            user = new User(
                    user.getUserId(),
                    user.getUsername(),
                    password,
                    fullName
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Profile updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Profile update failed.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void logout() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame().setVisible(true);
        }
    }

}
