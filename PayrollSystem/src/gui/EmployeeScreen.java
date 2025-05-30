package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EmployeeScreen extends JFrame {
    private JTextField idField, nameField, departmentField, positionField;
    private DefaultTableModel tableModel;

    public EmployeeScreen() {
        setTitle("Employee Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Employee"));

        inputPanel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        inputPanel.add(departmentField);

        inputPanel.add(new JLabel("Position:"));
        positionField = new JTextField();
        inputPanel.add(positionField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addEmployee());
        inputPanel.add(new JLabel()); // spacer
        inputPanel.add(addButton);

        // Table to show employees
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Department", "Position"}, 0);
        JTable employeeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Employee List"));

        // Layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private void addEmployee() {
        String id = idField.getText();
        String name = nameField.getText();
        String department = departmentField.getText();
        String position = positionField.getText();

        if (id.isEmpty() || name.isEmpty() || department.isEmpty() || position.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Add to table (you’ll connect this to backend later)
        tableModel.addRow(new Object[]{id, name, department, position});
        clearFields();
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        departmentField.setText("");
        positionField.setText("");
    }
}
// To run this screen, you can create a main method in a separate class or add it to your existing Dashboard class
// to open it when the "Employees" button is clicked.