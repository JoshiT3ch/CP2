import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewPayrollForm extends JFrame {
    private JTable payrollTable;

    public ViewPayrollForm() {
        setTitle("View Payroll Records");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("📁 Payroll Records");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBounds(250, 20, 300, 30);
        add(title);

        payrollTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(payrollTable);
        scrollPane.setBounds(30, 80, 620, 350);
        add(scrollPane);

        loadPayrollData();

        setVisible(true);
    }

    private void loadPayrollData() {
        List<Payroll> payrolls = FileManager.loadPayroll("payroll.dat");

        String[] columns = {"Employee ID", "Full Name", "Hours Worked", "Rate/hr", "Total Pay"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Payroll p : payrolls) {
            model.addRow(new Object[]{
                p.getEmployeeId(),
                p.getFullName(),
                p.getHoursWorked(),
                String.format("₱%.2f", p.getRatePerHour()),
                String.format("₱%.2f", p.getTotalPay())
            });
        }

        payrollTable.setModel(model);
    }
}
//         payrollTable.setRowHeight(30);
//         payrollTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));