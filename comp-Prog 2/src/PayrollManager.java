import java.util.*;

public class PayrollManager {

    public static List<Payroll> generatePayroll(List<Employee> employees, List<AttendanceRecord> attendanceRecords, Date fromDate, Date toDate) {
        Map<String, Double> hoursWorkedMap = new HashMap<>();

        for (AttendanceRecord record : attendanceRecords) {
            if (!isWithinRange(record.getDate(), fromDate, toDate)) continue;

            String id = record.getEmployeeId();
            double hours = record.getHoursWorked();

            hoursWorkedMap.put(id, hoursWorkedMap.getOrDefault(id, 0.0) + hours);
        }

        List<Payroll> payrollList = new ArrayList<>();

        for (Employee emp : employees) {
            double hoursWorked = hoursWorkedMap.getOrDefault(emp.getIdNumber(), 0.0);
            double totalPay = hoursWorked * emp.getRatePerHour();

            Payroll payroll = new Payroll(emp.getIdNumber(), emp.getFullName(), hoursWorked, emp.getRatePerHour(), totalPay);
            payrollList.add(payroll);
        }

        return payrollList;
    }

    private static boolean isWithinRange(Date date, Date start, Date end) {
        return !date.before(start) && !date.after(end);
    }
}
//     public static List<Payroll> loadPayroll(String filePath) {
//         List<Payroll> payrolls = new ArrayList<>();
//         File file = new File(filePath);
//         if (!file.exists()) return payrolls;