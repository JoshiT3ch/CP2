import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idNumber;
    private String fullName;
    private int age;
    private String gender;
    private String origin;
    private String role;
    private double ratePerHour;
    private String photoPath;

    public Employee(String idNumber, String fullName, int age, String gender, String origin, String role, double ratePerHour, String photoPath) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.origin = origin;
        this.role = role;
        this.ratePerHour = ratePerHour;
        this.photoPath = photoPath;
    }

    // Getters
    public String getIdNumber() {
        return idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getOrigin() {
        return origin;
    }

    public String getRole() {
        return role;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    // Setters
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return fullName + " (" + role + ")";
    }
}
// This class represents an Employee with various attributes such as id, name, age