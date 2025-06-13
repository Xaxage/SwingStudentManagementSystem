package dao;

import model.Mark;

import java.util.List;

public interface MarkDAO {
  List<Mark> getMarksByStudent(int studentId);

  List<Mark> getMarksBySubject(int subjectId);

  boolean addOrUpdateMark(Mark mark);

  boolean deleteMark(int studentId, int subjectId);

  Double getStudentSubjectMark(int studentId, int subjectId);
}
