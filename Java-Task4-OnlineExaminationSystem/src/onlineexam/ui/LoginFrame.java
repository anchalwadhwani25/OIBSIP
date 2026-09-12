package onlineexam.ui;

import onlineexam.dao.ExamDAO;
import onlineexam.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private ExamDAO examDAO;

    public LoginFrame() {

        examDAO = new ExamDAO();

        setTitle("Online Examination System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Online Examination System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(80, 25, 300, 30);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 85, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(170, 85, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 125, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 125, 200, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 170, 100, 30);
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(145, 220, 160, 30);
        panel.add(createAccountButton);

        loginButton.addActionListener(e -> login());

        createAccountButton.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });

        add(panel);
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        User user = examDAO.login(username, password);

        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!\nWelcome " + user.getFullName(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            new ProfileFrame(user).setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
