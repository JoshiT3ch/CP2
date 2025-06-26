import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import com.formdev.flatlaf.FlatLightLaf;

public class AddEmployeeForm extends JFrame {
    private JTextField idField, nameField, ageField, originField, roleField, rateField, addressField;
    private JComboBox<String> genderBox;
    private JLabel photoLabel;
    private File selectedPhoto;

    public AddEmployeeForm() {
        FlatLightLaf.setup();
        SwingUtilities.updateComponentTreeUI(this);

        setTitle("➕ Add New Employee");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Employee", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        formPanel.setBackground(new Color(245, 245, 250));
        add(formPanel, BorderLayout.CENTER);

        idField = createTextField();      addRow(formPanel, "ID Number:", idField);
        nameField = createTextField();    addRow(formPanel, "Full Name:", nameField);
        ageField = createTextField();     addRow(formPanel, "Age:", ageField);

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        addRow(formPanel, "Gender:", genderBox);

        originField = createTextField();  addRow(formPanel, "Origin:", originField);
        roleField = createTextField();    addRow(formPanel, "Role:", roleField);
        rateField = createTextField();    addRow(formPanel, "Rate/hr:", rateField);
        addressField = createTextField(); addRow(formPanel, "Address:", addressField);

        photoLabel = new JLabel("📸 Drop Photo Here", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(300, 150));
        photoLabel.setBorder(new LineBorder(Color.GRAY));
        photoLabel.setBackground(new Color(230, 230, 230));
        photoLabel.setOpaque(true);
        photoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        new DropTarget(photoLabel, new DropTargetAdapter() {
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable t = evt.getTransferable();
                    List<File> dropped = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                    if (!dropped.isEmpty()) {
                        selectedPhoto = dropped.get(0);
                        photoLabel.setText(selectedPhoto.getName());
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
        add(photoPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(245, 245, 250));
        JButton saveBtn = createButton("💾 Save");
        JButton cancelBtn = createButton("❌ Cancel");

        saveBtn.addActionListener(e -> saveEmployee());
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addRow(JPanel panel, String label, JComponent input) {
        JLabel lbl = new JLabel(label, SwingConstants.RIGHT);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lbl);
        panel.add(input);
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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

    private void saveEmployee() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderBox.getSelectedItem().toString();
            String origin = originField.getText();
            String role = roleField.getText();
            double rate = Double.parseDouble(rateField.getText());
            String address = addressField.getText();
            String photoPath = selectedPhoto != null ? selectedPhoto.getAbsolutePath() : "";

            Employee emp = new Employee(id, name, age, gender, origin, role, rate, photoPath);
            List<Employee> employees = FileManager.loadEmployees("employees.dat");
            employees.add(emp);
            FileManager.saveEmployees(employees, "employees.dat");

            JOptionPane.showMessageDialog(this, "✅ Employee saved!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error: " + e.getMessage());
        }
    }
}