import java.awt.*;
import java.util.List;
import javax.swing.*;

public class EmployeeProfileDetails extends JFrame {
    public EmployeeProfileDetails(Employee emp) {
        setTitle("👤 Employee Profile");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Employee Details", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 250));
        add(mainPanel, BorderLayout.CENTER);

        // === LEFT SIDE: Photo ===
        JPanel left = new JPanel();
        left.setBackground(new Color(245, 245, 250));
        left.setPreferredSize(new Dimension(200, 300));

        JLabel photo = new JLabel("No Photo", SwingConstants.CENTER);
        photo.setPreferredSize(new Dimension(180, 180));
        photo.setOpaque(true);
        photo.setBackground(new Color(230, 230, 230));
        photo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        photo.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        if (emp.getPhotoPath() != null && !emp.getPhotoPath().isEmpty()) {
            ImageIcon icon = new ImageIcon(emp.getPhotoPath());
            Image scaled = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            photo.setText(null);
            photo.setIcon(new ImageIcon(scaled));
        }

        left.add(photo);
        mainPanel.add(left, BorderLayout.WEST);

        // === RIGHT SIDE: Info Grid ===
        JPanel right = new JPanel(new GridLayout(0, 1, 5, 10));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        right.setBackground(new Color(245, 245, 250));

        right.add(infoRow("🆔 ID:", emp.getIdNumber()));
        right.add(infoRow("👤 Name:", emp.getFullName()));
        right.add(infoRow("🎂 Age:", String.valueOf(emp.getAge())));
        right.add(infoRow("⚥ Gender:", emp.getGender()));
        right.add(infoRow("🌍 Origin:", emp.getOrigin()));
        right.add(infoRow("💼 Role:", emp.getRole()));
        right.add(infoRow("⏱ Rate/hr:", emp.getRatePerHour() + " PHP"));

        mainPanel.add(right, BorderLayout.CENTER);

        // === Bottom Buttons ===
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bottomPanel.setBackground(new Color(245, 245, 250));

        JButton editBtn = new JButton("✏️ Edit");
        editBtn.setFocusPainted(false);
        editBtn.addActionListener(e -> {
            dispose();
            new EditEmployeeForm(emp);
        });

        JButton deleteBtn = new JButton("🗑 Delete");
        deleteBtn.setFocusPainted(false);
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                List<Employee> employees = FileManager.loadEmployees("employees.dat");
                employees.removeIf(e1 -> e1.getIdNumber().equals(emp.getIdNumber()));
                FileManager.saveEmployees(employees, "employees.dat");
                JOptionPane.showMessageDialog(this, "✅ Employee deleted successfully.");
                dispose();
                new EmployeeProfilesForm();
            }
        });

        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel infoRow(String label, String value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(new Color(245, 245, 250));

        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel v = new JLabel(value);
        v.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        v.setForeground(Color.DARK_GRAY);

        row.add(l, BorderLayout.WEST);
        row.add(v, BorderLayout.CENTER);
        return row;
    }
} 