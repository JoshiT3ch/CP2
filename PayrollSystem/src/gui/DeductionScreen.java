package gui;

import javax.swing.*;
import java.awt.*;

public class DeductionScreen extends JFrame {
    private JComboBox<String> employeeDropdown;
    private JTextField deductionTypeField;
    private JTextField deductionAmountField;

    public DeductionScreen() {
        setTitle("Deduction Management");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Employee:"));
        employeeDropdown = new JComboBox<>(new String[]{"John Doe", "Jane Smith"}); // Replace with actual employee list
        add(employeeDropdown);

        add(new JLabel("Deduction Type:"));
        deductionTypeField = new JTextField();
        add(deductionTypeField);

        add(new JLabel("Amount:"));
        deductionAmountField = new JTextField();
        add(deductionAmountField);

        JButton submitButton = new JButton("Add Deduction");
        submitButton.addActionListener(e -> submitDeduction());
        add(new JLabel()); // empty cell
        add(submitButton);
    }

    private void submitDeduction() {
        String employee = (String) employeeDropdown.getSelectedItem();
        String type = deductionTypeField.getText();
        String amountStr = deductionAmountField.getText();

        try {
            double amount = Double.parseDouble(amountStr);

            // Call your backend here to save the deduction
            JOptionPane.showMessageDialog(this,
                    "Deduction saved:\n" + employee + " - " + type + " - $" + amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }
}
// To run this screen, you can create a main method in a separate class or add it to your existing Dashboard class
// to open it when the "Deductions" button is clicked.