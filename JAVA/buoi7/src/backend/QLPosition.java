package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

    @Override
    public void addPosition(String positionName) {
        String sql = "INSERT INTO `Position` (Position_Name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, positionName);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Thêm Position thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePosition(int id) {
        String sql = "DELETE FROM `Position` WHERE Position_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Xóa Position thành công!");
            else System.out.println("-> Không tìm thấy Position có ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePositionName(int id, String newName) {
        String sql = "UPDATE `Position` SET Position_Name = ? WHERE Position_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("-> Cập nhật tên Position thành công!");
            else System.out.println("-> Không tìm thấy Position có ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}