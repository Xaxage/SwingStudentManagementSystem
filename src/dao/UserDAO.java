package dao;

import model.User;

public interface UserDAO {
    User getUserByEmail(String email);
    boolean validateUser(String email, String password);
}
