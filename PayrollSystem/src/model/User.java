package model;

public class User {
    private String username;
    private String password;
    private String role;       // e.g., admin, hr, payroll_officer
    private String employeeID; // Links this user to an employee record

    public User(String username, String password, String role, String employeeID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.employeeID = employeeID;
    }

    public User(String username2, String password2) {
        //TODO Auto-generated constructor stub
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
