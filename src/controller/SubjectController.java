package controller;

import dao.SubjectDAO;
import dao.SubjectDAOImpl;
import model.Subject;
import view.SubjectView;

import java.util.List;

public class SubjectController {
  private SubjectDAO subjectDAO;
  private SubjectView subjectView;

  public SubjectController(SubjectView subjectView) {
    this.subjectDAO = new SubjectDAOImpl();
    this.subjectView = subjectView;
  }

  public List<Subject> getAllSubjects() {
    return subjectDAO.getAllSubjects();
  }

  public boolean addSubject(String name) {
    return subjectDAO.addSubject(new Subject(0, name));
  }

  public boolean updateSubject(int id, String name) {
    return subjectDAO.updateSubject(new Subject(id, name));
  }

  public boolean deleteSubject(int id) {
    return subjectDAO.deleteSubject(id);
  }

  public void refreshSubjectView() {
    subjectView.displaySubjects(getAllSubjects());
  }
}
