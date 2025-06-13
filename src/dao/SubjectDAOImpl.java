package dao;

import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
  private Connection connection;

  public SubjectDAOImpl() {
    this.connection = DatabaseConnection.getInstance().getConnection();
  }

  @Override
  public List<Subject> getAllSubjects() {
    List<Subject> subjects = new ArrayList<>();
    String sql = "SELECT * FROM subjects";

    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

      while (resultSet.next()) {
        subjects.add(new Subject(
          resultSet.getInt("id"),
          resultSet.getString("name")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return subjects;
  }

  @Override
  public Subject getSubjectById(int id) {
    String sql = "SELECT * FROM subjects WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Subject(
          resultSet.getInt("id"),
          resultSet.getString("name")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean addSubject(Subject subject) {
    String sql = "INSERT INTO subjects (name) VALUES (?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, subject.getName());
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateSubject(Subject subject) {
    String sql = "UPDATE subjects SET name = ? WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, subject.getName());
      statement.setInt(2, subject.getId());
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteSubject(int id) {
    String sql = "DELETE FROM subjects WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, id);
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
