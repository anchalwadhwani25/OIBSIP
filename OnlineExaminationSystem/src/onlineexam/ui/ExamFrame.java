package onlineexam.ui;

import onlineexam.dao.ExamDAO;
import onlineexam.model.Question;
import onlineexam.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ExamFrame extends JFrame {

    private User user;
    private List<Question> questions;
    private int currentQuestion = 0;
    private int[] answers;

    private JLabel questionNumberLabel;
    private JLabel questionLabel;

    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;

    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;

    private JLabel timerLabel;

    private Timer timer;

    private final int initialSeconds = 1200;
    private int remainingSeconds = 1200;

    public ExamFrame(User user) {

        this.user = user;

        ExamDAO examDAO = new ExamDAO();
        questions = examDAO.getAllQuestions();

        if (questions.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No questions found in database.",
                    "Exam Error",
                    JOptionPane.ERROR_MESSAGE
            );

            dispose();
            return;
        }

        answers = new int[questions.size()];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = -1;
        }

        setTitle("Online Examination - " + user.getFullName());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                        ExamFrame.this,
                        "Are you sure you want to quit the exam?\nYour progress will be lost.",
                        "Quit Exam",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (choice == JOptionPane.YES_OPTION) {

                    if (timer != null) {
                        timer.stop();
                    }

                    dispose();
                    new ProfileFrame(user).setVisible(true);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Online Examination");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(30, 20, 300, 30);
        panel.add(titleLabel);

        timerLabel = new JLabel("Time: 20:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setBounds(550, 20, 110, 30);
        panel.add(timerLabel);

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionNumberLabel.setBounds(30, 70, 300, 25);
        panel.add(questionNumberLabel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBounds(30, 110, 620, 50);
        panel.add(questionLabel);

        optionA = new JRadioButton();
        optionA.setBounds(50, 180, 580, 30);
        panel.add(optionA);

        optionB = new JRadioButton();
        optionB.setBounds(50, 220, 580, 30);
        panel.add(optionB);

        optionC = new JRadioButton();
        optionC.setBounds(50, 260, 580, 30);
        panel.add(optionC);

        optionD = new JRadioButton();
        optionD.setBounds(50, 300, 580, 30);
        panel.add(optionD);

        optionGroup = new ButtonGroup();
        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        optionGroup.add(optionD);

        previousButton = new JButton("Previous");
        previousButton.setBounds(50, 370, 110, 35);
        panel.add(previousButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(180, 370, 110, 35);
        panel.add(nextButton);

        submitButton = new JButton("Submit Exam");
        submitButton.setBounds(450, 370, 140, 35);
        panel.add(submitButton);

        previousButton.addActionListener(e -> previousQuestion());

        nextButton.addActionListener(e -> nextQuestion());

        submitButton.addActionListener(e -> submitExam());

        add(panel);

        showQuestion();

        startTimer();
    }

    private void showQuestion() {

        Question question = questions.get(currentQuestion);

        questionNumberLabel.setText(
                "Question " + (currentQuestion + 1)
                        + " of " + questions.size()
        );

        questionLabel.setText(
                "<html>" + question.getQuestion() + "</html>"
        );

        optionA.setText("A. " + question.getOptionA());
        optionB.setText("B. " + question.getOptionB());
        optionC.setText("C. " + question.getOptionC());
        optionD.setText("D. " + question.getOptionD());

        optionGroup.clearSelection();

        if (answers[currentQuestion] == 0) {

            optionA.setSelected(true);

        } else if (answers[currentQuestion] == 1) {

            optionB.setSelected(true);

        } else if (answers[currentQuestion] == 2) {

            optionC.setSelected(true);

        } else if (answers[currentQuestion] == 3) {

            optionD.setSelected(true);
        }

        previousButton.setEnabled(currentQuestion > 0);

        nextButton.setEnabled(
                currentQuestion < questions.size() - 1
        );
    }

    private void saveAnswer() {

        if (optionA.isSelected()) {

            answers[currentQuestion] = 0;

        } else if (optionB.isSelected()) {

            answers[currentQuestion] = 1;

        } else if (optionC.isSelected()) {

            answers[currentQuestion] = 2;

        } else if (optionD.isSelected()) {

            answers[currentQuestion] = 3;

        } else {

            answers[currentQuestion] = -1;
        }
    }

    private void nextQuestion() {

        saveAnswer();

        if (currentQuestion < questions.size() - 1) {

            currentQuestion++;

            showQuestion();
        }
    }

    private void previousQuestion() {

        saveAnswer();

        if (currentQuestion > 0) {

            currentQuestion--;

            showQuestion();
        }
    }

    private void startTimer() {

        timer = new Timer(1000, e -> {

            remainingSeconds--;

            int minutes = remainingSeconds / 60;
            int seconds = remainingSeconds % 60;

            timerLabel.setText(
                    String.format(
                            "Time: %02d:%02d",
                            minutes,
                            seconds
                    )
            );

            if (remainingSeconds <= 0) {

                timer.stop();

                JOptionPane.showMessageDialog(
                        this,
                        "Time is over. Your exam will be submitted automatically.",
                        "Time Up",
                        JOptionPane.INFORMATION_MESSAGE
                );

                submitExamAutomatically();
            }
        });

        timer.start();
    }

    private void submitExam() {

        saveAnswer();

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to submit the exam?",
                "Submit Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            if (timer != null) {
                timer.stop();
            }

            int score = calculateScore();

            int timeTakenSeconds =
                    initialSeconds - remainingSeconds;

            dispose();

            new ResultFrame(
                    user,
                    score,
                    questions.size(),
                    timeTakenSeconds
            ).setVisible(true);
        }
    }

    private void submitExamAutomatically() {

        saveAnswer();

        int score = calculateScore();

        int timeTakenSeconds =
                initialSeconds - remainingSeconds;

        dispose();

        new ResultFrame(
                user,
                score,
                questions.size(),
                timeTakenSeconds
        ).setVisible(true);
    }

    private int calculateScore() {

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {

            String correctAnswer =
                    questions.get(i).getCorrectAnswer();

            int correctIndex = -1;

            if (correctAnswer.equalsIgnoreCase("A")) {

                correctIndex = 0;

            } else if (correctAnswer.equalsIgnoreCase("B")) {

                correctIndex = 1;

            } else if (correctAnswer.equalsIgnoreCase("C")) {

                correctIndex = 2;

            } else if (correctAnswer.equalsIgnoreCase("D")) {

                correctIndex = 3;
            }

            if (answers[i] == correctIndex) {
                score++;
            }
        }

        return score;
    }

}
