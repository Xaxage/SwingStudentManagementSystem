package view;

import model.Student;
import model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MarkView extends JFrame {
  private final JComboBox<Student> studentComboBox;
  private final JComboBox<Subject> subjectComboBox;
  private final JTextField markField;
  private final JButton addButton;
  private final JButton deleteButton;
  private final JButton backButton;
  private final JTable markTable;
  private final DefaultTableModel tableModel;

  public MarkView() {
    setTitle("Mark Management");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    // Table setup
    tableModel = new DefaultTableModel(new Object[]{"Student", "Subject", "Mark"}, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Make table non-editable
      }
    };
    markTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(markTable);

    // Form setup
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
    formPanel.add(new JLabel("Student:"));
    studentComboBox = new JComboBox<>();
    formPanel.add(studentComboBox);
    formPanel.add(new JLabel("Subject:"));
    subjectComboBox = new JComboBox<>();
    formPanel.add(subjectComboBox);
    formPanel.add(new JLabel("Mark:"));
    markField = new JTextField();
    formPanel.add(markField);

    // Button setup
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    addButton = new JButton("Add/Update");
    deleteButton = new JButton("Delete");
    backButton = new JButton("Back");
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(backButton);

    // Main layout
    setLayout(new BorderLayout(10, 10));
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Add selection listener to update mark field when selection changes
    studentComboBox.addActionListener(e -> updateMarkField());
    subjectComboBox.addActionListener(e -> updateMarkField());
  }

  private void updateMarkField() {
    Student student = getSelectedStudent();
    Subject subject = getSelectedSubject();
    if (student != null && subject != null) {
      markField.setText("");
    }
  }

  public void displayStudentsAndSubjects(List<Student> students, List<Subject> subjects) {
    studentComboBox.removeAllItems();
    subjectComboBox.removeAllItems();

    for (Student student : students) {
      studentComboBox.addItem(student);
    }

    for (Subject subject : subjects) {
      subjectComboBox.addItem(subject);
    }
  }

  public void displayMarks(List<Object[]> marks) {
    tableModel.setRowCount(0); // Clear existing rows
    for (Object[] mark : marks) {
      tableModel.addRow(mark);
    }
  }

  public Student getSelectedStudent() {
    return (Student) studentComboBox.getSelectedItem();
  }

  public Subject getSelectedSubject() {
    return (Subject) subjectComboBox.getSelectedItem();
  }

  public double getMark() {
    try {
      return Double.parseDouble(markField.getText());
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public void clearForm() {
    markField.setText("");
  }

  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public void showSuccess(String message) {
    JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
  }

  public void addAddButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  public void addDeleteButtonListener(ActionListener listener) {
    deleteButton.addActionListener(listener);
  }

  public void addBackButtonListener(ActionListener listener) {
    backButton.addActionListener(listener);
  }
}
