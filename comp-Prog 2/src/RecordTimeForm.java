import java.awt.*;
import javax.swing.*;

public class RecordTimeForm extends JFrame {

    public RecordTimeForm() {
        setTitle("🕒 Record Attendance (Test)");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("This is a test form!", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(label);

        setVisible(true);
    }
}
