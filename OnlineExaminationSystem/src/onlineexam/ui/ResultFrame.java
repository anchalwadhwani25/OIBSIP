package onlineexam.ui;

import onlineexam.model.User;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {

    private User user;
    private int score;
    private int totalQuestions;
    private int timeTakenSeconds;

    public ResultFrame(
            User user,
            int score,
            int totalQuestions,
            int timeTakenSeconds
    ) {

        this.user = user;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.timeTakenSeconds = timeTakenSeconds;

        setTitle("Exam Result");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Examination Result");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(120, 30, 280, 35);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel(
                "Student: " + user.getFullName()
        );
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        nameLabel.setBounds(80, 90, 350, 25);
        panel.add(nameLabel);

        JLabel scoreLabel = new JLabel(
                "Score: " + score + " / " + totalQuestions
        );
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
        scoreLabel.setBounds(150, 135, 250, 30);
        panel.add(scoreLabel);

        int incorrect = totalQuestions - score;

        JLabel correctLabel = new JLabel(
                "Correct Answers: " + score
        );
        correctLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        correctLabel.setBounds(150, 185, 250, 25);
        panel.add(correctLabel);

        JLabel incorrectLabel = new JLabel(
                "Incorrect Answers: " + incorrect
        );
        incorrectLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        incorrectLabel.setBounds(150, 220, 250, 25);
        panel.add(incorrectLabel);

        double percentage =
                ((double) score / totalQuestions) * 100;

        JLabel percentageLabel = new JLabel(
                String.format(
                        "Percentage: %.2f%%",
                        percentage
                )
        );
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        percentageLabel.setBounds(145, 260, 250, 30);
        panel.add(percentageLabel);

        int minutes = timeTakenSeconds / 60;
        int seconds = timeTakenSeconds % 60;

        JLabel timeLabel = new JLabel(
                String.format(
                        "Time Taken: %02d:%02d",
                        minutes,
                        seconds
                )
        );
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLabel.setBounds(150, 300, 250, 25);
        panel.add(timeLabel);

        String result;

        if (percentage >= 50) {
            result = "Result: PASSED";
        } else {
            result = "Result: FAILED";
        }

        JLabel resultLabel = new JLabel(result);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setBounds(170, 340, 200, 30);
        panel.add(resultLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(170, 395, 150, 35);
        panel.add(logoutButton);

        logoutButton.addActionListener(e -> logout());

        add(panel);
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
