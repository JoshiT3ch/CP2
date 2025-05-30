package model;

public class Employee {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String positionID;
    private String departmentID;

    public Employee(String employeeID, String firstName, String lastName, String email,
                    String phone, String address, String positionID, String departmentID) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.positionID = positionID;
        this.departmentID = departmentID;
    }

    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPositionID() {
        return positionID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    // Setters
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }
}
