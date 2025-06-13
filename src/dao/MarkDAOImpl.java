package dao;

import model.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkDAOImpl implements MarkDAO {
  private Connection connection;

  public MarkDAOImpl() {
    this.connection = DatabaseConnection.getInstance().getConnection();
  }

  @Override
  public List<Mark> getMarksByStudent(int studentId) {
    List<Mark> marks = new ArrayList<>();
    String sql = "SELECT * FROM student_subject WHERE student_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, studentId);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        marks.add(new Mark(
          resultSet.getInt("student_id"),
          resultSet.getInt("subject_id"),
          resultSet.getDouble("mark")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return marks;
  }

  @Override
  public List<Mark> getMarksBySubject(int subjectId) {
    List<Mark> marks = new ArrayList<>();
    String sql = "SELECT * FROM student_subject WHERE subject_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, subjectId);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        marks.add(new Mark(
          resultSet.getInt("student_id"),
          resultSet.getInt("subject_id"),
          resultSet.getDouble("mark")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return marks;
  }

  @Override
  public boolean addOrUpdateMark(Mark mark) {
    String sql = "INSERT INTO student_subject (student_id, subject_id, mark) VALUES (?, ?, ?) " +
      "ON CONFLICT (student_id, subject_id) DO UPDATE SET mark = EXCLUDED.mark";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, mark.getStudentId());
      statement.setInt(2, mark.getSubjectId());
      statement.setDouble(3, mark.getMark());
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteMark(int studentId, int subjectId) {
    String sql = "DELETE FROM student_subject WHERE student_id = ? AND subject_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, studentId);
      statement.setInt(2, subjectId);
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Double getStudentSubjectMark(int studentId, int subjectId) {
    String sql = "SELECT mark FROM student_subject WHERE student_id = ? AND subject_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, studentId);
      statement.setInt(2, subjectId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return resultSet.getDouble("mark");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
