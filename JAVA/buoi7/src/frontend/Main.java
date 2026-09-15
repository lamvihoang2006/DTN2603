package frontend;

import backend.*;

public class Main {
    public static void main(String[] args) {
        IQLDepartment qlDepartment = new QLDepartment();
        IQLPosition qlPosition = new QLPosition();
        IQLAccount qlAccount = new QLAccount();

        System.out.println("\n==== TEST DEPARTMENT ====");
        qlDepartment.addDepartment("Phòng Nhân Sự");
        qlDepartment.updateDepartmentName(1, "Phòng Giám Đốc");
        qlDepartment.showAllDepartments();

        System.out.println("\n==== TEST POSITION ====");
        qlPosition.addPosition("DEV");
        qlPosition.showAllPositions();

        System.out.println("\n==== TEST ACCOUNT ====");
        qlAccount.addAccount("test@gmail.com", "testuser", "Nguyen Van Test", 1, 1);
        qlAccount.updateUsername(1, "user_moi_update");
        qlAccount.showAllAccounts();
    }
}