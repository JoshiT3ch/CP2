import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class AttendanceHistoryForm extends JFrame {
    private JSpinner dateSpinner;
    private JTextArea historyArea;

    public AttendanceHistoryForm() {
        FlatLightLaf.setup();

        setTitle("Attendance History");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 250));

        JLabel title = new JLabel("📆 Attendance History", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(title, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(245, 245, 250));
        topPanel.add(new JLabel("Select Date:"));

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        dateSpinner.setPreferredSize(new Dimension(120, 30));
        topPanel.add(dateSpinner);

        JButton searchBtn = new JButton("🔍 View Records");
        searchBtn.setBackground(new Color(100, 149, 237));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.addActionListener(e -> loadAttendance());
        topPanel.add(searchBtn);

        panel.add(topPanel, BorderLayout.CENTER);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(historyArea);
        scroll.setPreferredSize(new Dimension(380, 250));
        panel.add(scroll, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void loadAttendance() {
        try {
            Date selectedDate = (Date) dateSpinner.getValue();
            String selectedDateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
            List<AttendanceRecord> records = FileManager.loadAttendance("attendance.dat");

            StringBuilder sb = new StringBuilder();
            sb.append("Records for ").append(selectedDateStr).append(":\n\n");

            boolean found = false;
            for (AttendanceRecord record : records) {
                String recordDateStr = new SimpleDateFormat("yyyy-MM-dd").format(record.getDate());
                if (recordDateStr.equals(selectedDateStr)) {
                    found = true;
                    sb.append("👤 Employee ID: ").append(record.getEmployeeId()).append("\n");
                    sb.append("⏰ Time In: ").append(record.getTimeIn()).append("\n");
                    sb.append("⏳ Time Out: ").append(record.getTimeOut()).append("\n");
                    sb.append("🕒 Hours Worked: ").append(String.format("%.2f", record.getHoursWorked())).append(" hrs\n");
                    sb.append("---------------------------\n");
                }
            }

            if (!found) {
                sb.append("No records found.");
            }

            historyArea.setText(sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Failed to load records: " + e.getMessage());
        }
    }
}
