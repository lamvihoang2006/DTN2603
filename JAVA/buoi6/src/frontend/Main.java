package frontend;

import backend.IQLAccount;
import backend.IQLDepartment;
import backend.IQLPosition;
import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;

public class Main {
    public static void main(String[] args) {
        IQLAccount qlAccount = new QLAccount();
        IQLDepartment qlDepartment = new QLDepartment();
        IQLPosition qlPosition = new QLPosition();

        qlDepartment.showAllDepartments();
        qlPosition.showAllPositions();
        qlAccount.showAllAccounts();
    }
}
