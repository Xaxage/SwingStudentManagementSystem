package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
  private JButton studentButton;
  private JButton subjectButton;
  private JButton markButton;
  private JButton logoutButton;

  public MainView() {
    setTitle("Student Management System");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    studentButton = new JButton("Manage Students");
    panel.add(studentButton);

    subjectButton = new JButton("Manage Subjects");
    panel.add(subjectButton);

    markButton = new JButton("Manage Marks");
    panel.add(markButton);

    logoutButton = new JButton("Logout");
    panel.add(logoutButton);

    add(panel);
  }

  public void addStudentButtonListener(ActionListener listener) {
    studentButton.addActionListener(listener);
  }

  public void addSubjectButtonListener(ActionListener listener) {
    subjectButton.addActionListener(listener);
  }

  public void addMarkButtonListener(ActionListener listener) {
    markButton.addActionListener(listener);
  }

  public void addLogoutButtonListener(ActionListener listener) {
    logoutButton.addActionListener(listener);
  }
}
