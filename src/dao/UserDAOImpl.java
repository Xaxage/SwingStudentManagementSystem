package dao;

import model.User;
import java.sql.*;

public class UserDAOImpl implements UserDAO {
  private Connection connection;

  public UserDAOImpl() {
    this.connection = DatabaseConnection.getInstance().getConnection();
  }

  @Override
  public User getUserByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new User(
          resultSet.getInt("id"),
          resultSet.getString("email"),
          resultSet.getString("password")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean validateUser(String email, String password) {
    User user = getUserByEmail(email);
    return user != null && user.getPassword().equals(password);
  }
}
