package onlineexam.ui;

import onlineexam.dao.ExamDAO;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private ExamDAO examDAO;

    public RegisterFrame() {

        examDAO = new ExamDAO();

        setTitle("Create Account");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Create Student Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(105, 25, 260, 30);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(60, 90, 100, 25);
        panel.add(nameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(170, 90, 200, 25);
        panel.add(fullNameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 135, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(170, 135, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 180, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 180, 200, 25);
        panel.add(passwordField);

        JButton registerButton = new JButton("Create Account");
        registerButton.setBounds(140, 235, 170, 35);
        panel.add(registerButton);

        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(140, 285, 170, 35);
        panel.add(backButton);

        registerButton.addActionListener(e -> register());

        backButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        add(panel);
    }

    private void register() {

        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (fullName.isEmpty() ||
                username.isEmpty() ||
                password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Registration Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean registered = examDAO.registerUser(
                username,
                password,
                fullName
        );

        if (registered) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!\nYou can now login.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
            new LoginFrame().setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists.\nPlease choose another username.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
