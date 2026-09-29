package backend.repository.impl;

import backend.repository.IDepartmentRepository;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DepartmentRepositoryImpl implements IDepartmentRepository {

    @Override
    public boolean isDepartmentNameExists(String departmentName) throws Exception {
        String sql = "SELECT 1 FROM Department WHERE Department_Name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, departmentName);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean addDepartment(String departmentName) throws Exception {
        String sql = "INSERT INTO Department (Department_Name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, departmentName);
            return pstmt.executeUpdate() > 0;
        }
    }
}