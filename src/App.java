import controller.AuthController;
import controller.MarkController;
import controller.StudentController;
import controller.SubjectController;
import model.Student;
import model.Subject;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
  public static void main(String[] args) {
    // Initialize views
    final LoginView loginView = new LoginView();
    final MainView mainView = new MainView();
    final StudentView studentView = new StudentView();
    final SubjectView subjectView = new SubjectView();
    final MarkView markView = new MarkView();

    // Initialize controllers
    final AuthController authController = new AuthController(loginView);
    final StudentController studentController = new StudentController(studentView);
    final SubjectController subjectController = new SubjectController(subjectView);
    final MarkController markController = new MarkController(markView);

    // Login button action
    loginView.addLoginListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String email = loginView.getEmail();
        String password = loginView.getPassword();

        if (authController.authenticate(email, password)) {
          loginView.setVisible(false);
          mainView.setVisible(true);
        } else {
          loginView.showError("Invalid email or password");
        }
      }
    });

    // Main view buttons
    mainView.addStudentButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        studentController.refreshStudentView();
        studentView.setVisible(true);
      }
    });

    mainView.addSubjectButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        subjectController.refreshSubjectView();
        subjectView.setVisible(true);
      }
    });

    mainView.addMarkButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        markController.refreshMarkView();
        markView.setVisible(true);
      }
    });

    mainView.addLogoutButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainView.setVisible(false);
        loginView.clearForm();
        loginView.setVisible(true);
      }
    });

    // Student view buttons
    studentView.addAddButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = studentView.getStudentName();
        int age = studentView.getStudentAge();

        if (name.isEmpty() || age < 0) {
          studentView.showError("Please enter valid name and age");
          return;
        }

        if (studentController.addStudent(name, age)) {
          studentController.refreshStudentView();
          studentView.clearForm();
          studentView.showSuccess("Student added successfully");
        } else {
          studentView.showError("Failed to add student");
        }
      }
    });

    studentView.addUpdateButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int id = studentView.getSelectedStudentId();
        String name = studentView.getStudentName();
        int age = studentView.getStudentAge();

        if (id < 0) {
          studentView.showError("Please select a student to update");
          return;
        }

        if (name.isEmpty() || age < 0) {
          studentView.showError("Please enter valid name and age");
          return;
        }

        if (studentController.updateStudent(id, name, age)) {
          studentController.refreshStudentView();
          studentView.clearForm();
          studentView.showSuccess("Student updated successfully");
        } else {
          studentView.showError("Failed to update student");
        }
      }
    });

    studentView.addDeleteButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int id = studentView.getSelectedStudentId();

        if (id < 0) {
          studentView.showError("Please select a student to delete");
          return;
        }

        if (studentController.deleteStudent(id)) {
          studentController.refreshStudentView();
          studentView.clearForm();
          studentView.showSuccess("Student deleted successfully");
        } else {
          studentView.showError("Failed to delete student");
        }
      }
    });

    studentView.addBackButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        studentView.setVisible(false);
      }
    });

    // Subject view buttons
    subjectView.addAddButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = subjectView.getSubjectName();

        if (name.isEmpty()) {
          subjectView.showError("Please enter a subject name");
          return;
        }

        if (subjectController.addSubject(name)) {
          subjectController.refreshSubjectView();
          subjectView.clearForm();
          subjectView.showSuccess("Subject added successfully");
        } else {
          subjectView.showError("Failed to add subject");
        }
      }
    });

    subjectView.addUpdateButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int id = subjectView.getSelectedSubjectId();
        String name = subjectView.getSubjectName();

        if (id < 0) {
          subjectView.showError("Please select a subject to update");
          return;
        }

        if (name.isEmpty()) {
          subjectView.showError("Please enter a subject name");
          return;
        }

        if (subjectController.updateSubject(id, name)) {
          subjectController.refreshSubjectView();
          subjectView.clearForm();
          subjectView.showSuccess("Subject updated successfully");
        } else {
          subjectView.showError("Failed to update subject");
        }
      }
    });

    subjectView.addDeleteButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int id = subjectView.getSelectedSubjectId();

        if (id < 0) {
          subjectView.showError("Please select a subject to delete");
          return;
        }

        if (subjectController.deleteSubject(id)) {
          subjectController.refreshSubjectView();
          subjectView.clearForm();
          subjectView.showSuccess("Subject deleted successfully");
        } else {
          subjectView.showError("Failed to delete subject");
        }
      }
    });

    subjectView.addBackButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        subjectView.setVisible(false);
      }
    });

    // Mark view buttons
    markView.addAddButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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

        if (markController.addOrUpdateMark(student.getId(), subject.getId(), mark)) {
          markController.refreshMarkView();
          markView.clearForm();
          markView.showSuccess("Mark updated successfully");
        } else {
          markView.showError("Failed to update mark");
        }
      }
    });

    markView.addDeleteButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Student student = markView.getSelectedStudent();
        Subject subject = markView.getSelectedSubject();

        if (student == null || subject == null) {
          markView.showError("Please select both student and subject");
          return;
        }

        if (markController.deleteMark(student.getId(), subject.getId())) {
          markController.refreshMarkView();
          markView.clearForm();
          markView.showSuccess("Mark deleted successfully");
        } else {
          markView.showError("Failed to delete mark");
        }
      }
    });

    markView.addBackButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        markView.setVisible(false);
      }
    });

    // Start with login view
    loginView.setVisible(true);
  }
}
