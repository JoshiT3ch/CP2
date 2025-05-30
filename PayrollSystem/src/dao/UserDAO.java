package dao;

import model.User;

public class UserDAO {

    // Dummy method for now, replace with DB logic
    public User authenticate(String username, String password) {
        // Example hardcoded user
        if (username.equals("admin") && password.equals("1234")) {
            return new User(username, password); // Make sure your User class has a constructor that accepts (String, String)
        }
        return null;
    }
}
