package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QLDepartment implements IQLDepartment {
    @Override
    public void showAllDepartments() {
        String sql = "SELECT * FROM Department";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- DANH SÁCH DEPARTMENT ---");
            System.out.printf("%-10s | %-25s%n", "ID", "Name");
            System.out.println("-------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d | %-25s%n",
                        rs.getInt("Department_ID"),
                        rs.getString("Department_Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}