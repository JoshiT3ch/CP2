import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        // Set FlatLaf for modern UI
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("⚠️ Failed to set look and feel");
        }

        SwingUtilities.invokeLater(() -> {
            new Login(); // ✅ Launch login screen first
        });
    }
}
            
