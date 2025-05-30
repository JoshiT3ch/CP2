package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private String employeeID;
    private LocalDate logDate;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private String activityLog;

    public Attendance(String employeeID, LocalDate logDate, LocalTime timeIn, LocalTime timeOut, String activityLog) {
        this.employeeID = employeeID;
        this.logDate = logDate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.activityLog = activityLog;
    }

    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public String getActivityLog() {
        return activityLog;
    }

    // Setters
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public void setActivityLog(String activityLog) {
        this.activityLog = activityLog;
    }
}
// End of Attendance.java
// This class represents an attendance record for an employee, including their ID, log date, time in, time out, and any activity logs.