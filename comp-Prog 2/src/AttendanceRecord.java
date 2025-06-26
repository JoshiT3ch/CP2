import java.io.Serializable;
import java.util.Date;

public class AttendanceRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private Date date;
    private String timeIn;
    private String timeOut;
    private double hoursWorked;

    public AttendanceRecord(String employeeId, Date date, String timeIn, String timeOut, double hoursWorked) {
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.hoursWorked = hoursWorked;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getDate() {
        return date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
}
