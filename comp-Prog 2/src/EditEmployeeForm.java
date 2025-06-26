import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class EditEmployeeForm extends JFrame {
    private JTextField idField, nameField, ageField, originField, roleField, rateField, addressField;
    private JComboBox<String> genderBox;
    private JLabel photoLabel;
    private File selectedPhoto;
    private Employee originalEmployee;

    public EditEmployeeForm(Employee emp) {
        this.originalEmployee = emp;

        setTitle("✏️ Edit Employee");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Edit Employee", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        formPanel.setBackground(new Color(245, 245, 250));
        add(formPanel, BorderLayout.CENTER);

        idField = createTextField(emp.getIdNumber());   addRow(formPanel, "ID Number:", idField);
        nameField = createTextField(emp.getFullName()); addRow(formPanel, "Full Name:", nameField);
        ageField = createTextField(String.valueOf(emp.getAge())); addRow(formPanel, "Age:", ageField);

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setSelectedItem(emp.getGender());
        addRow(formPanel, "Gender:", genderBox);

        originField = createTextField(emp.getOrigin()); addRow(formPanel, "Origin:", originField);
        roleField = createTextField(emp.getRole());     addRow(formPanel, "Role:", roleField);
        rateField = createTextField(String.valueOf(emp.getRatePerHour())); addRow(formPanel, "Rate/hr:", rateField);
        addressField = createTextField(""); // Placeholder for now
        addRow(formPanel, "Address:", addressField);

        // Drag & Drop Photo
        photoLabel = new JLabel("📸 Drop New Photo", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(300, 150));
        photoLabel.setBorder(new LineBorder(Color.GRAY));
        photoLabel.setBackground(new Color(230, 230, 230));
        photoLabel.setOpaque(true);
        photoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        if (emp.getPhotoPath() != null && !emp.getPhotoPath().isEmpty()) {
            photoLabel.setText(null);
            ImageIcon icon = new ImageIcon(emp.getPhotoPath());
            Image scaled = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaled));
        }

        new DropTarget(photoLabel, new DropTargetAdapter() {
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable t = evt.getTransferable();
                    java.util.List<File> dropped = (java.util.List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                    if (!dropped.isEmpty()) {
                        selectedPhoto = dropped.get(0);
                        photoLabel.setText(selectedPhoto.getName());
                        photoLabel.setIcon(null);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel photoPanel = new JPanel();
        photoPanel.setBackground(new Color(245, 245, 250));
        photoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        photoPanel.add(photoLabel);
        add(photoPanel, BorderLayout.SOUTH);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(245, 245, 250));
        JButton saveBtn = createButton("💾 Save Changes");
        JButton cancelBtn = createButton("❌ Cancel");

        saveBtn.addActionListener(e -> updateEmployee());
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void addRow(JPanel panel, String label, JComponent input) {
        JLabel lbl = new JLabel(label, SwingConstants.RIGHT);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lbl);
        panel.add(input);
    }

    private JTextField createTextField(String value) {
        JTextField tf = new JTextField(value);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return tf;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(100, 149, 237));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void updateEmployee() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderBox.getSelectedItem().toString();
            String origin = originField.getText();
            String role = roleField.getText();
            double rate = Double.parseDouble(rateField.getText());
            String photoPath = selectedPhoto != null ? selectedPhoto.getAbsolutePath() : originalEmployee.getPhotoPath();

            Employee updatedEmp = new Employee(id, name, age, gender, origin, role, rate, photoPath);

            List<Employee> employees = FileManager.loadEmployees("employees.dat");
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getIdNumber().equals(originalEmployee.getIdNumber())) {
                    employees.set(i, updatedEmp);
                    break;
                }
            }

            FileManager.saveEmployees(employees, "employees.dat");
            JOptionPane.showMessageDialog(this, "✅ Employee updated!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error: " + e.getMessage());
        }
    }
}
