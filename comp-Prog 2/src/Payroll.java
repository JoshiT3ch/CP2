import java.io.Serializable;

public class Payroll implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String fullName;
    private double hoursWorked;
    private double ratePerHour;
    private double totalPay;

    public Payroll(String employeeId, String fullName, double hoursWorked, double ratePerHour, double totalPay) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.hoursWorked = hoursWorked;
        this.ratePerHour = ratePerHour;
        this.totalPay = totalPay;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public double getTotalPay() {
        return totalPay;
    }
}
    