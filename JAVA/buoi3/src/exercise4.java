import java.util.Scanner;

public class exercise4 {
    public static void question1() {
        System.out.println("--- Question 1 ---");
        String s = "   Học  lập   trình Java   ";

        String[] words = s.trim().split("\\s+");
        System.out.println("Chuỗi ban đầu: '" + s + "'");
        System.out.println("Số lượng từ trong chuỗi là: " + words.length);
    }

    public static void question2() {
        System.out.println("\n--- Question 2 ---");
        String s1 = "Hello ";
        String s2 = "VTI Academy";

        System.out.println("Sau khi nối chuỗi: " + s1.concat(s2));
    }

    public static void question3(Scanner scanner) {
        System.out.println("\n--- Question 3 ---");
        System.out.print("Nhập vào tên của bạn: ");
        String name = scanner.nextLine();

        String firstChar = name.substring(0, 1).toUpperCase();
        String leftChar = name.substring(1).toLowerCase();

        System.out.println("Tên sau khi chuẩn hóa: " + firstChar + leftChar);
    }

    public static void question4(Scanner scanner) {
        System.out.println("\n--- Question 4 ---");
        System.out.print("Nhập vào tên của bạn: ");
        String name = scanner.nextLine();

        name = name.toUpperCase();
        for (int i = 0; i < name.length(); i++) {
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + name.charAt(i));
        }
    }

    public static void question5(Scanner scanner) {
        System.out.println("\n--- Question 5 ---");
        System.out.print("Nhập vào Họ: ");
        String lastName = scanner.nextLine();

        System.out.print("Nhập vào Tên: ");
        String firstName = scanner.nextLine();

        System.out.println("Họ và tên đầy đủ: " + lastName + " " + firstName);
    }

    public static void question6(Scanner scanner) {
        System.out.println("\n--- Question 6 ---");
        System.out.print("Nhập vào Họ và tên đầy đủ: ");
        String fullName = scanner.nextLine();

        String[] words = fullName.trim().split("\\s+");

        String lastName = words[0];
        String firstName = words[words.length - 1];

        String middleName = "";
        for (int i = 1; i < words.length - 1; i++) {
            middleName += words[i] + " ";
        }

        System.out.println("Họ là: " + lastName);
        System.out.println("Tên đệm là: " + middleName.trim());
        System.out.println("Tên là: " + firstName);
    }

    public static void question7(Scanner scanner) {
        System.out.println("\n--- Question 7 ---");
        System.out.print("Nhập vào họ tên chưa chuẩn hóa: ");
        String name = scanner.nextLine();

        name = name.trim().replaceAll("\\s+", " ");

        String[] words = name.split(" ");

        String cleanName = "";
        for (String word : words) {
            String firstLetter = word.substring(0, 1).toUpperCase();
            String restOfWord = word.substring(1).toLowerCase();
            cleanName += firstLetter + restOfWord + " ";
        }

        System.out.println("Họ tên sau khi chuẩn hóa: '" + cleanName.trim() + "'");
    }

    public static void question8(Group[] groups) {
        System.out.println("\n--- Question 8 ---");
        if (groups == null) return;
        System.out.println("Các group có CHỨA chữ 'Java':");
        for (Group group : groups) {
            if (group.groupName.contains("Java")) {
                System.out.println("- " + group.groupName);
            }
        }
    }

    public static void question9(Group[] groups) {
        System.out.println("\n--- Question 9 ---");
        if (groups == null) return;

        System.out.println("Các group BẰNG CHÍNH XÁC chữ 'Java':");
        for (Group group : groups) {
            if (group.groupName.equals("Java")) {
                System.out.println("- " + group.groupName);
            }
        }
    }
    public static void question10() {
        System.out.println("\n--- Question 10 ---");
        String s1 = "word";
        String s2 = "drow";

        String reversedS2 = new StringBuilder(s2).reverse().toString();

        if (s1.equals(reversedS2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

    public static void question11() {
        System.out.println("\n--- Question 11 ---");
        String text = "Java Backend Academy";

        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'a') {
                count++;
            }
        }

        System.out.println("Chuỗi: '" + text + "'");
        System.out.println("Số lần ký tự 'a' xuất hiện: " + count);
    }

    public static void question12() {
        System.out.println("\n--- Question 12 ---");
        String text = "lam vi hoang";
        String reversed = "";

        for (int i = text.length() - 1; i >= 0; i--) {
            reversed += text.charAt(i);
        }

        System.out.println("Chuỗi gốc: " + text);
        System.out.println("Đảo ngược: " + reversed);
    }

    public static void question13() {
        System.out.println("\n--- Question 13 ---");
        String str1 = "abc";
        String str2 = "1abc";

        System.out.println("Chuỗi '" + str1 + "' không chứa số: " + checkNoDigit(str1));
        System.out.println("Chuỗi '" + str2 + "' không chứa số: " + checkNoDigit(str2));
    }

    private static boolean checkNoDigit(String s) {
        if (s == null) return false;
        return !s.matches(".*\\d.*");
    }

    public static void question14() {
        System.out.println("\n--- Question 14 ---");
        String text = "VTI Academy";
        String replaced = text.replace('e', '*');
        System.out.println("Ban đầu: " + text);
        System.out.println("Sau khi thay 'e' thành '*': " + replaced);
    }

    public static void question15() {
        System.out.println("\n--- Question 15 ---");
        String str = " I am developer ";
        String[] words = str.trim().split("\\s+");

        String reversedByWord = "";
        for (int i = words.length - 1; i >= 0; i--) {
            reversedByWord += words[i] + " ";
        }

        System.out.println("Ban đầu: '" + str + "'");
        System.out.println("Đảo ngược: '" + reversedByWord.trim() + "'");
    }

    public static void question16() {
        System.out.println("\n--- Question 16 ---");
        String str = "123456789";
        int n = 3;

        System.out.println("Chia chuỗi '" + str + "' thành phần " + n + " ký tự:");
        if (str.length() % n != 0) {
            System.out.println("KO");
        } else {
            for (int i = 0; i < str.length(); i += n) {
                System.out.println(str.substring(i, i + n));
            }
        }
    }
}
