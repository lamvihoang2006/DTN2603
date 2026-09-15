package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

    @Override
    public void addAccount(String email, String username, String fullName, int departmentId, int positionId) {
        String sql = "INSERT INTO Account (Email, Username, Full_Name, Department_ID, Position_ID, Create_Date) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, fullName);
            pstmt.setInt(4, departmentId);
            pstmt.setInt(5, positionId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Thêm Account thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi: Có thể Department_ID hoặc Position_ID không tồn tại, hoặc Email/Username bị trùng lặp!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(int id) {
        String sql = "DELETE FROM Account WHERE Account_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Xóa Account thành công!");
            else System.out.println("-> Không tìm thấy Account có ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsername(int id, String newUsername) {
        String sql = "UPDATE Account SET Username = ? WHERE Account_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Cập nhật Username thành công!");
            else System.out.println("-> Không tìm thấy Account có ID: " + id);
        } catch (Exception e) {
            System.out.println("Lỗi: Có thể Username mới đã bị trùng lặp (Unique)!");
            e.printStackTrace();
        }
    }
}