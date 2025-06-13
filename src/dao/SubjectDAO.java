package dao;

import model.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> getAllSubjects();

    Subject getSubjectById(int id);

    boolean addSubject(Subject subject);

    boolean updateSubject(Subject subject);

    boolean deleteSubject(int id);
}
