package view;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentView extends JFrame {
  private JTable studentTable;
  private DefaultTableModel tableModel;
  private JButton addButton;
  private JButton updateButton;
  private JButton deleteButton;
  private JButton backButton;
  private JTextField nameField;
  private JTextField ageField;

  public StudentView() {
    setTitle("Student Management");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    // Table setup
    tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age"}, 0);
    studentTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(studentTable);

    // Form setup
    JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    formPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    formPanel.add(nameField);
    formPanel.add(new JLabel("Age:"));
    ageField = new JTextField();
    formPanel.add(ageField);

    // Button setup
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    addButton = new JButton("Add");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    backButton = new JButton("Back");
    buttonPanel.add(addButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(backButton);

    // Main layout
    setLayout(new BorderLayout(10, 10));
    add(scrollPane, BorderLayout.CENTER);
    add(formPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  public void displayStudents(List<Student> students) {
    tableModel.setRowCount(0);
    for (Student student : students) {
      tableModel.addRow(new Object[]{student.getId(), student.getName(), student.getAge()});
    }
  }

  public int getSelectedStudentId() {
    int selectedRow = studentTable.getSelectedRow();
    if (selectedRow >= 0) {
      return (int) tableModel.getValueAt(selectedRow, 0);
    }
    return -1;
  }

  public String getStudentName() {
    return nameField.getText();
  }

  public int getStudentAge() {
    try {
      return Integer.parseInt(ageField.getText());
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public void clearForm() {
    nameField.setText("");
    ageField.setText("");
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

  public void addUpdateButtonListener(ActionListener listener) {
    updateButton.addActionListener(listener);
  }

  public void addDeleteButtonListener(ActionListener listener) {
    deleteButton.addActionListener(listener);
  }

  public void addBackButtonListener(ActionListener listener) {
    backButton.addActionListener(listener);
  }
}
