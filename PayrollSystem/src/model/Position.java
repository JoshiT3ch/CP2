package model;

public class Position {
    private String positionID;
    private String jobTitle;
    private String description;
    private double salaryRate; // Hourly or Monthly depending on your design

    public Position(String positionID, String jobTitle, String description, double salaryRate) {
        this.positionID = positionID;
        this.jobTitle = jobTitle;
        this.description = description;
        this.salaryRate = salaryRate;
    }

    // Getters
    public String getPositionID() {
        return positionID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public double getSalaryRate() {
        return salaryRate;
    }

    // Setters
    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalaryRate(double salaryRate) {
        this.salaryRate = salaryRate;
    }
}
//     }
//