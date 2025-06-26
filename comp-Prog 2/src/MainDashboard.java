import java.awt.*;
import javax.swing.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Main Dashboard");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("👋 Welcome to Payroll System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBounds(50, 20, 300, 30);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        JButton addBtn = createButton("➕ Add Employee");
        addBtn.setBounds(100, 70, 200, 40);
        addBtn.addActionListener(e -> new AddEmployeeForm());
        add(addBtn);

        JButton viewBtn = createButton("📋 View All Employees");
        viewBtn.setBounds(100, 130, 200, 40);
        viewBtn.addActionListener(e -> new EmployeeProfilesForm());
        add(viewBtn);

        JButton dashboardBtn = createButton("📊 Dashboard");
        dashboardBtn.setBounds(100, 190, 200, 40);
        dashboardBtn.addActionListener(e -> new DashboardForm());
        add(dashboardBtn);

        JButton attendanceBtn = createButton("🕒 Record Attendance");
attendanceBtn.setBounds(100, 250, 200, 40);
attendanceBtn.addActionListener(e -> {
    try {
        System.out.println("Opening RecordTimeForm...");
        new RecordTimeForm();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "⚠️ Failed to open Record Attendance Form:\n" + ex.getMessage());
    }
});
add(attendanceBtn);

JButton viewAttendanceHistoryBtn = createButton("📆 View Attendance History");
viewAttendanceHistoryBtn.setBounds(100, 310, 200, 40); // 👈 moved down to avoid overlap
viewAttendanceHistoryBtn.addActionListener(e -> new AttendanceHistoryForm());
add(viewAttendanceHistoryBtn);

JButton logoutBtn = createButton("🔓 Logout");
logoutBtn.setBounds(100, 370, 200, 40); // 👈 moved down as well
logoutBtn.addActionListener(e -> {
    dispose();
    new Login();
});
add(logoutBtn);


        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBackground(new Color(240, 240, 240));
        btn.setFocusPainted(false);
        return btn;
    }
}    
