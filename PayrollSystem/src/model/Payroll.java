package model;

public class Payroll {
    private String employeeID;
    private double grossSalary;
    private double netSalary;
    private double bonus;
    private Deduction deduction;
    private int totalHoursWorked;

    public Payroll(String employeeID, double grossSalary, double bonus, int totalHoursWorked, Deduction deduction) {
        this.employeeID = employeeID;
        this.grossSalary = grossSalary;
        this.bonus = bonus;
        this.totalHoursWorked = totalHoursWorked;
        this.deduction = deduction;
        calculateNetSalary();
    }

    private void calculateNetSalary() {
        if (deduction != null) {
            this.netSalary = grossSalary + bonus - deduction.getTotalDeductions();
        } else {
            this.netSalary = grossSalary + bonus;
        }
    }

    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public Deduction getDeduction() {
        return deduction;
    }

    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }

    // Setters
    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
        calculateNetSalary();
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
        calculateNetSalary();
    }

    public void setDeduction(Deduction deduction) {
        this.deduction = deduction;
        calculateNetSalary();
    }

    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
// End of Payroll.java
// This class represents the payroll information for an employee, including gross salary, net salary, bonus, deductions, and total hours worked.