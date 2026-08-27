import java.util.Random;
import java.util.Scanner;

public class exercise1 {
    public static void question1() {
        System.out.println("--- Question 1 ---");
        float salaryAcc1 = 5240.5f;
        float salaryAcc2 = 10970.055f;

        int roundSalary1 = (int) salaryAcc1;
        int roundSalary2 = (int) salaryAcc2;

        System.out.println("Lương Account 1 sau làm tròn: " + roundSalary1);
        System.out.println("Lương Account 2 sau làm tròn: " + roundSalary2);
    }

    public static int generateSecretNumber() {
        Random random = new Random();
        return random.nextInt(100000);
    }

    public static void question2(int randNum) {
        System.out.println("\n--- Question 2 ---");

        System.out.println("Số ngẫu nhiên 5 chữ số: " + String.format("%05d", randNum));
    }

    public static void question3(int numQuestion2) {
        System.out.println("\n--- Question 3 ---");
        int lastTwo = numQuestion2 % 100;

        System.out.println("2 số cuối của q2 là: " + String.format("%02d", lastTwo));
    }

    public static void question4() {
        System.out.println("\n--- Question 4 ---");
        Scanner scanner = new Scanner(System.in);

        int so1, so2;

        System.out.print("Nhập số bị chia: ");
        so1 = scanner.nextInt();

        do {
            System.out.print("Nhập số chia (phải khác 0): ");
            so2 = scanner.nextInt();

            if (so2 == 0) {
                System.out.println("Lỗi: Số bạn nhập là 0. Vui lòng nhập lại!");
            }
        } while (so2 == 0);

        float result = (float) so1 / so2;

        System.out.println("Đáp án là: " + result);
    }
}
