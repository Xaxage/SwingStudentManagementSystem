package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
  private JTextField emailField;
  private JPasswordField passwordField;
  private JButton loginButton;

  public LoginView() {
    setTitle("Student Management System - Login");
    setSize(350, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    panel.add(new JLabel("Email:"));
    emailField = new JTextField();
    panel.add(emailField);

    panel.add(new JLabel("Password:"));
    passwordField = new JPasswordField();
    panel.add(passwordField);

    loginButton = new JButton("Login");
    panel.add(loginButton);

    add(panel);
  }

  public String getEmail() {
    return emailField.getText();
  }

  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  public void addLoginListener(ActionListener listener) {
    loginButton.addActionListener(listener);
  }

  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
  }

  public void clearForm() {
    emailField.setText("");
    passwordField.setText("");
  }
}
