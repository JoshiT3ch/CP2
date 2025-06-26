import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RecordTimeForm extends JFrame {
    private JComboBox<String> employeeCombo;
    private JSpinner dateSpinner, timeInSpinner, timeOutSpinner;
    private JButton saveBtn;

    public RecordTimeForm() {
        setTitle("Time In / Out");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Record Attendance", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 20));
        formPanel.setBackground(new Color(245, 245, 250));
        formPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

        employeeCombo = new JComboBox<>();
        for (Employee emp : FileManager.loadEmployees("employees.dat")) {
            employeeCombo.addItem(emp.getIdNumber() + " - " + emp.getFullName());
        }

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        timeInSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeInEditor = new JSpinner.DateEditor(timeInSpinner, "HH:mm");
        timeInSpinner.setEditor(timeInEditor);

        timeOutSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeOutEditor = new JSpinner.DateEditor(timeOutSpinner, "HH:mm");
        timeOutSpinner.setEditor(timeOutEditor);

        formPanel.add(new JLabel("👤 Employee:"));
        formPanel.add(employeeCombo);
        formPanel.add(new JLabel("🗓 Date:"));
        formPanel.add(dateSpinner);
        formPanel.add(new JLabel("⏰ Time In:"));
        formPanel.add(timeInSpinner);
        formPanel.add(new JLabel("⏳ Time Out:"));
        formPanel.add(timeOutSpinner);

        add(formPanel, BorderLayout.CENTER);

        saveBtn = new JButton("💾 Save Record");
        saveBtn.setBackground(new Color(100, 149, 237));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setFocusPainted(false);
        saveBtn.addActionListener(e -> saveRecord());

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(245, 245, 250));
        btnPanel.add(saveBtn);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveRecord() {
        try {
            int index = employeeCombo.getSelectedIndex();
            if (index < 0) throw new Exception("No employee selected.");
            Employee emp = FileManager.loadEmployees("employees.dat").get(index);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            Date dateVal = (Date) dateSpinner.getValue();
            Date timeInVal = (Date) timeInSpinner.getValue();
            Date timeOutVal = (Date) timeOutSpinner.getValue();

            String date = new SimpleDateFormat("yyyy-MM-dd").format(dateVal);
            String timeIn = new SimpleDateFormat("HH:mm").format(timeInVal);
            String timeOut = new SimpleDateFormat("HH:mm").format(timeOutVal);

            LocalTime in = LocalTime.parse(timeIn, timeFormatter);
            LocalTime out = LocalTime.parse(timeOut, timeFormatter);
            long minutesWorked = ChronoUnit.MINUTES.between(in, out);
            double hoursWorked = minutesWorked / 60.0;

            double dailySalary = hoursWorked * emp.getRatePerHour();

            AttendanceRecord record = new AttendanceRecord(emp.getIdNumber(), date, timeIn, timeOut);
            java.util.List<AttendanceRecord> records = FileManager.loadAttendance("attendance.dat");
            records.add(record);
            FileManager.saveAttendance(records, "attendance.dat");

            JOptionPane.showMessageDialog(this, String.format("✅ Attendance recorded!\n⏱ Hours worked: %.2f\n💰 Salary: PHP %.2f", hoursWorked, dailySalary));
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error: " + e.getMessage());
        }
    }
}
