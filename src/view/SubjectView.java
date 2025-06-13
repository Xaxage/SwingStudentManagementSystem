package view;

import model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SubjectView extends JFrame {
  private final JTable subjectTable;
  private final DefaultTableModel tableModel;
  private final JButton addButton;
  private final JButton updateButton;
  private final JButton deleteButton;
  private final JButton backButton;
  private final JTextField nameField;

  public SubjectView() {
    setTitle("Subject Management");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    // Table setup
    tableModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0);
    subjectTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(subjectTable);

    // Form setup
    JPanel formPanel = new JPanel(new GridLayout(1, 2, 5, 5));
    formPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    formPanel.add(nameField);

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

  public void displaySubjects(List<Subject> subjects) {
    tableModel.setRowCount(0);
    for (Subject subject : subjects) {
      tableModel.addRow(new Object[]{subject.getId(), subject.getName()});
    }
  }

  public int getSelectedSubjectId() {
    int selectedRow = subjectTable.getSelectedRow();
    if (selectedRow >= 0) {
      return (int) tableModel.getValueAt(selectedRow, 0);
    }
    return -1;
  }

  public String getSubjectName() {
    return nameField.getText();
  }

  public void clearForm() {
    nameField.setText("");
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
