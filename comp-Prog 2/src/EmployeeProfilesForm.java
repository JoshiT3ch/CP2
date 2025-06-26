import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class EmployeeProfilesForm extends JFrame {
    private JPanel employeeListPanel;
    private JTextField searchField;
    private List<Employee> allEmployees;

    public EmployeeProfilesForm() {
        setTitle("Employee Profiles");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        allEmployees = FileManager.loadEmployees("employees.dat");

        // Top Search Bar
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField();
        topPanel.add(searchLabel, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Panel that holds employee cards
        employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new GridBagLayout()); // Fix: now respects card layout
        employeeListPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(employeeListPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Search Logic
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase();
                List<Employee> filtered = allEmployees.stream()
                        .filter(emp -> emp.getFullName().toLowerCase().contains(query)
                                || emp.getRole().toLowerCase().contains(query))
                        .collect(Collectors.toList());
                displayEmployees(filtered);
            }
        });

        displayEmployees(allEmployees);

        setVisible(true);
    }

    private void displayEmployees(List<Employee> employees) {
        employeeListPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        int y = 0;
        for (Employee emp : employees) {
            JPanel card = createEmployeeCard(emp);
            gbc.gridy = y++;
            employeeListPanel.add(card, gbc);
        }

        employeeListPanel.revalidate();
        employeeListPanel.repaint();
    }

    private JPanel createEmployeeCard(Employee emp) {
        JPanel card = new JPanel(null);
        card.setPreferredSize(new Dimension(540, 90));
        card.setMaximumSize(new Dimension(540, 90));
        card.setBackground(new Color(255, 255, 255));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Profile Photo
        JLabel photoLabel = new JLabel();
        photoLabel.setBounds(10, 10, 70, 70);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if (emp.getPhotoPath() != null && !emp.getPhotoPath().isEmpty()) {
            ImageIcon icon = new ImageIcon(emp.getPhotoPath());
            Image scaled = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaled));
        } else {
            photoLabel.setText("No Photo");
            photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        card.add(photoLabel);

        // Name + Role
        JLabel nameLabel = new JLabel(emp.getFullName() + " - " + emp.getRole());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setBounds(90, 10, 400, 20);
        card.add(nameLabel);

        // ID
        JLabel idLabel = new JLabel("ID: " + emp.getIdNumber());
        idLabel.setBounds(90, 30, 200, 15);
        card.add(idLabel);

        // Gender + Age
        JLabel gendLabel = new JLabel("Gender: " + emp.getGender() + " | Age: " + emp.getAge());
        gendLabel.setBounds(90, 50, 300, 15);
        card.add(gendLabel);

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new EmployeeProfileDetails(emp);
            }
        });

        return card;
    }
}
// To run this code, ensure you have the Employee class and FileManager class implemented