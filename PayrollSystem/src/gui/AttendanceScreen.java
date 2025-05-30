package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AttendanceScreen extends JFrame {

    private JComboBox<String> employeeDropdown;
    private JTextField dateField;
    private JComboBox<String> statusDropdown;

    public AttendanceScreen() {
        setTitle("Attendance Input");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Don't close app when closing this
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Employee:"));
        employeeDropdown = new JComboBox<>(new String[]{"John Doe", "Jane Smith"}); // Replace with backend data
        add(employeeDropdown);

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Status:"));
        statusDropdown = new JComboBox<>(new String[]{"Present", "Absent", "Late", "On Leave"});
        add(statusDropdown);

        add(new JLabel()); // empty label for spacing

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitAttendance());
        add(submitButton);
    }

    private void submitAttendance() {
        String employee = (String) employeeDropdown.getSelectedItem();
        String date = dateField.getText();
        String status = (String) statusDropdown.getSelectedItem();

        // Here you'd call your backend to save attendance
        JOptionPane.showMessageDialog(this, "Attendance recorded:\n" +
                employee + " - " + date + " - " + status);
    }
}
