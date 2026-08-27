public class exercise3 {
    public static void question1() {
        System.out.println("--- Question 1 ---");
        Integer salaryInteger = 5000;

        float salaryFloat = salaryInteger.floatValue();

        System.out.printf("Lương sau khi convert sang float: %.2f%n", salaryFloat);
    }

    public static void question2() {
        System.out.println("\n--- Question 2 ---");
        String s = "1234567";

        int num = Integer.parseInt(s);
        System.out.println("Số int sau khi convert từ String: " + num);
    }

    // Question 3: Convert Integer sang int
    public static void question3() {
        System.out.println("\n--- Question 3 ---");
        Integer numInteger = Integer.valueOf("1234567");

        int numInt = numInteger.intValue();
        System.out.println("Số int sau khi convert từ Integer: " + numInt);
    }
}
