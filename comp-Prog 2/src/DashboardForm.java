import java.awt.*;
import java.util.HashSet;
import java.util.List;
import javax.swing.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {
        setTitle("Employee Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 3, 20, 20));
        getContentPane().setBackground(Color.WHITE);

        List<Employee> employees = FileManager.loadEmployees("employees.dat");

   


        int total = employees.size();
        int male = (int) employees.stream().filter(e -> e.getGender().equalsIgnoreCase("Male")).count();
        int female = (int) employees.stream().filter(e -> e.getGender().equalsIgnoreCase("Female")).count();
        double avgRate = employees.stream().mapToDouble(Employee::getRatePerHour).average().orElse(0.0);
        double avgAge = employees.stream().mapToInt(Employee::getAge).average().orElse(0.0);
        int roleCount = new HashSet<>(employees.stream().map(Employee::getRole).toList()).size();

        add(createStatCard("👥 Total Employees", String.valueOf(total)));
        add(createStatCard("👨‍🦰 Total Males", String.valueOf(male)));
        add(createStatCard("👩 Total Females", String.valueOf(female)));
        add(createStatCard("🧑‍💼 Unique Roles", String.valueOf(roleCount)));
        add(createStatCard("💸 Avg Rate/hr", String.format("₱%.2f", avgRate)));
        add(createStatCard("🎂 Avg Age", String.format("%.1f yrs", avgAge)));

        setVisible(true);
    }

    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}
//                 employeeListPanel.add(card);
//             }