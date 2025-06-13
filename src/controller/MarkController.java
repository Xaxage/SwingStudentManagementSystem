package controller;

import dao.*;
import model.Mark;
import model.Student;
import model.Subject;
import view.MarkView;

import java.util.ArrayList;
import java.util.List;

public class MarkController {
  private final MarkDAO markDAO;
  private final StudentDAO studentDAO;
  private final SubjectDAO subjectDAO;
  private final MarkView markView;

  public MarkController(MarkView markView) {
    this.markDAO = new MarkDAOImpl();
    this.studentDAO = new StudentDAOImpl();
    this.subjectDAO = new SubjectDAOImpl();
    this.markView = markView;

    initializeListeners();
    refreshMarkView();
  }

  private void initializeListeners() {
    markView.addAddButtonListener(e -> handleAddOrUpdateMark());
    markView.addDeleteButtonListener(e -> handleDeleteMark());
    markView.addBackButtonListener(e -> markView.setVisible(false));
  }

  public List<Student> getAllStudents() {
    return studentDAO.getAllStudents();
  }

  public List<Subject> getAllSubjects() {
    return subjectDAO.getAllSubjects();
  }

  public Double getStudentSubjectMark(int studentId, int subjectId) {
    return markDAO.getStudentSubjectMark(studentId, subjectId);
  }

  public boolean addOrUpdateMark(int studentId, int subjectId, double mark) {
    return markDAO.addOrUpdateMark(new Mark(studentId, subjectId, mark));
  }

  public boolean deleteMark(int studentId, int subjectId) {
    return markDAO.deleteMark(studentId, subjectId);
  }

  public void refreshMarkView() {
    // Load students and subjects
    List<Student> students = getAllStudents();
    List<Subject> subjects = getAllSubjects();
    markView.displayStudentsAndSubjects(students, subjects);

    // Load and display marks
    List<Object[]> marksData = new ArrayList<>();
    for (Student student : students) {
      for (Subject subject : subjects) {
        Double mark = getStudentSubjectMark(student.getId(), subject.getId());
        if (mark != null) {
          marksData.add(new Object[]{
            student.getName(),
            subject.getName(),
            mark
          });
        }
      }
    }
    markView.displayMarks(marksData);
  }

  private void handleAddOrUpdateMark() {
    Student student = markView.getSelectedStudent();
    Subject subject = markView.getSelectedSubject();
    double mark = markView.getMark();

    if (student == null || subject == null) {
      markView.showError("Please select both student and subject");
      return;
    }

    if (mark < 0 || mark > 100) {
      markView.showError("Please enter a valid mark (0-100)");
      return;
    }

    if (addOrUpdateMark(student.getId(), subject.getId(), mark)) {
      refreshMarkView();
      markView.clearForm();
      markView.showSuccess("Mark updated successfully");
    } else {
      markView.showError("Failed to update mark");
    }
  }

  private void handleDeleteMark() {
    Student student = markView.getSelectedStudent();
    Subject subject = markView.getSelectedSubject();

    if (student == null || subject == null) {
      markView.showError("Please select both student and subject");
      return;
    }

    if (deleteMark(student.getId(), subject.getId())) {
      refreshMarkView();
      markView.clearForm();
      markView.showSuccess("Mark deleted successfully");
    } else {
      markView.showError("Failed to delete mark");
    }
  }
}
