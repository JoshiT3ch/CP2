import javax.swing.*;
import java.util.List;

public class DeleteEmployee {

    public static void deleteEmployeeById(String idToDelete, JFrame parentFrame) {
        int confirm = JOptionPane.showConfirmDialog(
                parentFrame,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            List<Employee> employees = FileManager.loadEmployees("employees.dat");

            boolean found = false;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getIdNumber().equals(idToDelete)) {
                    employees.remove(i);
                    found = true;
                    break;
                }
            }

            if (found) {
                FileManager.saveEmployees(employees, "employees.dat");
                JOptionPane.showMessageDialog(parentFrame, "Employee deleted successfully.");
                parentFrame.dispose(); // Close profile window if applicable
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Employee not found.");
            }
        }
    }
}
// This class provides functionality to delete an employee by ID, prompting for confirmation and updating the employee list accordingly.
// It uses the FileManager to load and save employee data, ensuring the changes are persistent.
// It also provides feedback to the user about the success or failure of the deletion operation.
//                 employeeListPanel.add(card);
//             }