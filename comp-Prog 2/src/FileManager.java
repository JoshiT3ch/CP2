import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // ==== EMPLOYEE HANDLERS ====

    public static void saveEmployees(List<Employee> employees, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> loadEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return employees;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            employees = (List<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // ==== ATTENDANCE HANDLERS ====

    public static void saveAttendance(List<AttendanceRecord> records, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AttendanceRecord> loadAttendance(String filePath) {
        List<AttendanceRecord> records = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return records;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            records = (List<AttendanceRecord>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    // ==== PAYROLL HANDLERS ====

    public static void savePayroll(List<Payroll> payrolls, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(payrolls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Payroll> loadPayroll(String filePath) {
        List<Payroll> payrolls = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return payrolls;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            payrolls = (List<Payroll>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
}
// Note: The Employee, AttendanceRecord, and Payroll classes must implement Serializable for this to work.
// Ensure that the file paths used in the save/load methods are valid and accessible.
// You may need to adjust the file paths based on your project structure and requirements.
//     }