package model;

public class Mark {
  private int studentId;
  private int subjectId;
  private double mark;

  public Mark(int studentId, int subjectId, double mark) {
    this.studentId = studentId;
    this.subjectId = subjectId;
    this.mark = mark;
  }

  public int getStudentId() {
    return studentId;
  }

  public int getSubjectId() {
    return subjectId;
  }

  public double getMark() {
    return mark;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public void setMark(double mark) {
    this.mark = mark;
  }
}
