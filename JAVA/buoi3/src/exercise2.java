import java.util.Date;

public class exercise2 {
    public static void question1() {
        System.out.println("--- Question 1 ---");

        Account[] accounts = new Account[5];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
            accounts[i].email = "Email " + (i + 1);
            accounts[i].userName = "User name " + (i + 1);
            accounts[i].fullName = "Full name " + (i + 1);
            accounts[i].createDate = new Date();

            System.out.printf("Account %d - Email: %s | Username: %s | FullName: %s | Date: %s%n",
                    (i + 1), accounts[i].email, accounts[i].userName, accounts[i].fullName, accounts[i].createDate);
        }
    }


}
