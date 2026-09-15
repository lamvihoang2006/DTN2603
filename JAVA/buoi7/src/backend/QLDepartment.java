package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

    @Override
    public void addDepartment(String departmentName) {
        String sql = "INSERT INTO Department (Department_Name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, departmentName);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Thêm Department thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartment(int id) {
        String sql = "DELETE FROM Department WHERE Department_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Xóa Department thành công!");
            else System.out.println("-> Không tìm thấy Department có ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartmentName(int id, String newName) {
        String sql = "UPDATE Department SET Department_Name = ? WHERE Department_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Cập nhật tên Department thành công!");
            else System.out.println("-> Không tìm thấy Department có ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}