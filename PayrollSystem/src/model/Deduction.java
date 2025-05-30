package model;

public class Deduction {
    private String employeeID;
    private double tax;
    private double sss;
    private double philHealth;
    private double totalDeductions;

    public Deduction(String employeeID, double tax, double sss, double philHealth) {
        this.employeeID = employeeID;
        this.tax = tax;
        this.sss = sss;
        this.philHealth = philHealth;
        calculateTotalDeductions();
    }

    private void calculateTotalDeductions() {
        this.totalDeductions = tax + sss + philHealth;
    }

    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public double getTax() {
        return tax;
    }

    public double getSSS() {
        return sss;
    }

    public double getPhilHealth() {
        return philHealth;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    // Setters
    public void setTax(double tax) {
        this.tax = tax;
        calculateTotalDeductions();
    }

    public void setSSS(double sss) {
        this.sss = sss;
        calculateTotalDeductions();
    }

    public void setPhilHealth(double philHealth) {
        this.philHealth = philHealth;
        calculateTotalDeductions();
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
// End of Deduction.java
// End of Deduction.java