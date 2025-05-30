package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("Employee Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton attendanceBtn = new JButton("Attendance");
        JButton payrollBtn = new JButton("Payroll");
        JButton deductionBtn = new JButton("Deductions");
        JButton employeeBtn = new JButton("Employees");

        // Add button listeners
        attendanceBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open Attendance Panel"));
        payrollBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open Payroll Panel"));
        deductionBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open Deduction Panel"));
        employeeBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open Employee Panel"));

        panel.add(attendanceBtn);
        panel.add(payrollBtn);
        panel.add(deductionBtn);
        panel.add(employeeBtn);

        add(panel);
    }
}
