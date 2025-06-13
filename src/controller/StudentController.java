package controller;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;
import view.StudentView;

import java.util.List;

public class StudentController {
  private final StudentDAO studentDAO;
  private final StudentView studentView;

  public StudentController(StudentView studentView) {
    this.studentDAO = new StudentDAOImpl();
    this.studentView = studentView;
  }

  public List<Student> getAllStudents() {
    return studentDAO.getAllStudents();
  }

  public boolean addStudent(String name, int age) {
    return studentDAO.addStudent(new Student(0, name, age));
  }

  public boolean updateStudent(int id, String name, int age) {
    return studentDAO.updateStudent(new Student(id, name, age));
  }

  public boolean deleteStudent(int id) {
    return studentDAO.deleteStudent(id);
  }

  public void refreshStudentView() {
    studentView.displayStudents(getAllStudents());
  }
}
