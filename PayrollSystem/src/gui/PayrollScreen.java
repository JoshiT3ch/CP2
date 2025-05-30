package gui;

import javax.swing.*;
import java.awt.*;

public class PayrollScreen extends JFrame {
    private JComboBox<String> employeeDropdown;
    private JTextField daysWorkedField;
    private JTextField grossPayField;
    private JTextField deductionField;
    private JTextField netPayField;

    public PayrollScreen() {
        setTitle("Payroll Generation");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Employee:"));
        employeeDropdown = new JComboBox<>(new String[]{"John Doe", "Jane Smith"}); // Replace with actual employee list
        add(employeeDropdown);

        add(new JLabel("Days Worked:"));
        daysWorkedField = new JTextField();
        add(daysWorkedField);

        add(new JLabel("Gross Pay:"));
        grossPayField = new JTextField();
        grossPayField.setEditable(false);
        add(grossPayField);

        add(new JLabel("Deductions:"));
        deductionField = new JTextField("0"); // Could also be fetched/calculated
        add(deductionField);

        add(new JLabel("Net Pay:"));
        netPayField = new JTextField();
        netPayField.setEditable(false);
        add(netPayField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculatePayroll());
        add(calculateButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> savePayroll());
        add(saveButton);
    }

    private void calculatePayroll() {
        try {
            int daysWorked = Integer.parseInt(daysWorkedField.getText());
            double dailyRate = 100.0; // Replace with actual employee rate later
            double gross = daysWorked * dailyRate;
            double deductions = Double.parseDouble(deductionField.getText());
            double net = gross - deductions;

            grossPayField.setText(String.format("%.2f", gross));
            netPayField.setText(String.format("%.2f", net));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    private void savePayroll() {
        // Hook this to your backend Payroll class
        JOptionPane.showMessageDialog(this, "Payroll saved successfully!");
    }

    // Main method to run the PayrollScreen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PayrollScreen payrollScreen = new PayrollScreen();
            payrollScreen.setVisible(true);
        });
    }
}