package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import view.LoginView;

public class AuthController {
  private final UserDAO userDAO;
  private LoginView loginView;

  public AuthController(LoginView loginView) {
    this.userDAO = new UserDAOImpl();
    this.loginView = loginView;
  }

  public boolean authenticate(String email, String password) {
    return userDAO.validateUser(email, password);
  }
}
