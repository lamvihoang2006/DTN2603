package frontend;

import backend.controller.AccountController;
import entity.Account;

import java.util.List;
import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private AccountController accountController;

    public Function() {
        scanner = new Scanner(System.in);
        accountController = new AccountController();
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ ACCOUNT =====");
            System.out.println("1. Hiển thị danh sách Account");
            System.out.println("2. Thêm mới Account");
            System.out.println("3. Sửa Username");
            System.out.println("4. Xóa Account");
            System.out.println("5. Thoát");
            System.out.print("Mời bạn chọn chức năng: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        hienThiDanhSach();
                        break;
                    case "2":
                        themAccount();
                        break;
                    case "3":
                        suaUsername();
                        break;
                    case "4":
                        xoaAccount();
                        break;
                    case "5":
                        System.out.println("Chương trình kết thúc!");
                        return;
                    default:
                        System.out.println("Sai chức năng! Vui lòng chọn 1-5.");
                }
            } catch (Exception e) {
                System.err.println("ĐÃ XẢY RA LỖI HỆ THỐNG: " + e.getMessage());
            }
        }
    }

    private void hienThiDanhSach() throws Exception {
        List<Account> accounts = accountController.getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("Danh sách Account đang trống!");
            return;
        }

        System.out.println("\n--- DANH SÁCH ACCOUNT ---");
        System.out.printf("%-5s | %-25s | %-15s | %-20s | %-15s | %-15s%n",
                "ID", "Email", "Username", "Fullname", "Phòng ban", "Vị trí");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        for (Account acc : accounts) {
            String depName = (acc.getDepartment() != null) ? acc.getDepartment().getDepartmentName() : "N/A";
            String posName = (acc.getPosition() != null) ? acc.getPosition().getPositionName() : "N/A";

            System.out.printf("%-5d | %-25s | %-15s | %-20s | %-15s | %-15s%n",
                    acc.getAccountID(), acc.getEmail(), acc.getUserName(), acc.getFullName(), depName, posName);
        }
    }

    private void themAccount() throws Exception {
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập Username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập Fullname: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập ID Phòng ban: ");
        int depId = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập ID Vị trí: ");
        int posId = Integer.parseInt(scanner.nextLine());

        if (accountController.addAccount(email, username, fullName, depId, posId)) {
            System.out.println("-> Thêm mới Account thành công!");
        } else {
            System.out.println("-> Thêm thất bại!");
        }
    }

    private void suaUsername() throws Exception {
        System.out.print("Nhập ID Account cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập Username mới: ");
        String newUsername = scanner.nextLine();

        if (accountController.updateUsername(id, newUsername)) {
            System.out.println("-> Cập nhật Username thành công!");
        } else {
            System.out.println("-> Cập nhật thất bại (Sai ID).");
        }
    }

    private void xoaAccount() throws Exception {
        System.out.print("Nhập ID Account cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (accountController.deleteAccount(id)) {
            System.out.println("-> Xóa Account thành công!");
        } else {
            System.out.println("-> Xóa thất bại (Sai ID).");
        }
    }
}