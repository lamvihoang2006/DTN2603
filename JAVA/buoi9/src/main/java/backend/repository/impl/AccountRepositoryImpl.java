package backend.repository.impl;

import backend.repository.IAccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {

    @Override
    public List<Account> getAllAccounts() throws Exception {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT acc.*, de.Department_Name, po.Position_Name \n" +
                "FROM Account acc \n" +
                "LEFT JOIN Department de ON acc.Department_ID = de.Department_ID \n" +
                "LEFT JOIN `Position` po ON acc.Position_ID = po.Position_ID";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Department department = null;
                if (rs.getString("Department_Name") != null) {
                    department = new Department(rs.getInt("Department_ID"), rs.getString("Department_Name"));
                }

                Position position = null;
                if (rs.getString("Position_Name") != null) {
                    position = new Position(rs.getInt("Position_ID"), rs.getString("Position_Name"));
                }

                Account account = new Account(
                        rs.getInt("Account_ID"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("Full_Name"),
                        department,
                        position
                );
                accountList.add(account);
            }
        }
        return accountList;
    }

    @Override
    public boolean addAccount(String email, String username, String fullName, int departmentId, int positionId) throws Exception {
        String sql = "INSERT INTO Account (Email, Username, Full_Name, Department_ID, Position_ID, Create_Date) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, fullName);
            pstmt.setInt(4, departmentId);
            pstmt.setInt(5, positionId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateUsername(int id, String newUsername) throws Exception {
        String sql = "UPDATE Account SET Username = ? WHERE Account_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteAccount(int id) throws Exception {
        String sql = "DELETE FROM Account WHERE Account_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public boolean isAccountExists(int id) throws Exception {
        String sql = "SELECT 1 FROM Account WHERE Account_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean isUsernameExists(String username) throws Exception {
        String sql = "SELECT 1 FROM Account WHERE Username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean isEmailExists(String email) throws Exception {
        String sql = "SELECT 1 FROM Account WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean isDepartmentExists(int id) throws Exception {
        String sql = "SELECT 1 FROM Department WHERE Department_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean isPositionExists(int id) throws Exception {
        String sql = "SELECT 1 FROM `Position` WHERE Position_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}