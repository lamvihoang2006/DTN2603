package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QLPosition implements IQLPosition {
    @Override
    public void showAllPositions() {
        String sql = "SELECT * FROM `Position`";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- DANH SÁCH POSITION ---");
            System.out.printf("%-10s | %-25s%n", "ID", "Name");
            System.out.println("-------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d | %-25s%n",
                        rs.getInt("Position_ID"),
                        rs.getString("Position_Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}