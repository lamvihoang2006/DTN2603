package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QLAccount implements IQLAccount {
    @Override
    public void showAllAccounts() {
        String sql = "SELECT * FROM Account";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- DANH SÁCH ACCOUNT ---");
            System.out.printf("%-10s | %-25s | %-20s | %-20s%n", "ID", "Email", "Username", "Fullname");
            System.out.println("--------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d | %-25s | %-20s | %-20s%n",
                        rs.getInt("Account_ID"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("Full_Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}