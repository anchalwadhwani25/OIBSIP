package onlineexam.dao;

import onlineexam.model.Question;
import onlineexam.model.User;
import onlineexam.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {
    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean registerUser(
            String username,
            String password,
            String fullName
    ) {

        String sql =
                "INSERT INTO users (username, password, full_name) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, fullName);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                return false;
            }

            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProfile(
            int userId,
            String fullName,
            String password
    ) {

        String sql =
                "UPDATE users SET full_name = ?, password = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fullName);
            statement.setString(2, password);
            statement.setInt(3, userId);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Question> getAllQuestions() {

        List<Question> questions = new ArrayList<>();

        String sql = "SELECT * FROM questions ORDER BY id";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Question question = new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("question"),
                        resultSet.getString("option_a"),
                        resultSet.getString("option_b"),
                        resultSet.getString("option_c"),
                        resultSet.getString("option_d"),
                        resultSet.getString("correct_answer")
                );

                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

}
