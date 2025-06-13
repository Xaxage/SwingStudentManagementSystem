package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
  private Connection connection;

  public StudentDAOImpl() {
    this.connection = DatabaseConnection.getInstance().getConnection();
  }

  @Override
  public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();
    String sql = "SELECT * FROM students";

    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

      while (resultSet.next()) {
        students.add(new Student(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getInt("age")
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }

  @Override
  public Student getStudentById(int id) {
    String sql = "SELECT * FROM students WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Student(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getInt("age")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean addStudent(Student student) {
    String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, student.getName());
      statement.setInt(2, student.getAge());
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateStudent(Student student) {
    String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, student.getName());
      statement.setInt(2, student.getAge());
      statement.setInt(3, student.getId());
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteStudent(int id) {
    String sql = "DELETE FROM students WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, id);
      return statement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
