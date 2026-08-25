import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department dep1 = new Department();
        dep1.departmentID = 1;
        dep1.departmentName = "Sale";

        Department dep2 = new Department();
        dep2.departmentID = 2;
        dep2.departmentName = "Marketing";

        Department dep3 = new Department();
        dep3.departmentID = 3;
        dep3.departmentName = "Giám đốc";

        Position pos1 = new Position();
        pos1.positionID = 1;
        pos1.positionName = Position.PositionName.DEV;

        Position pos2 = new Position();
        pos2.positionID = 2;
        pos2.positionName = Position.PositionName.TEST;

        Account acc1 = new Account();
        acc1.accountID = 1;
        acc1.email = "NguyenVanA@gmail.com";
        acc1.userName = "nguyenvana";
        acc1.fullName = "Nguyễn Văn A";
        acc1.departmentID = dep1;
        acc1.PositionID = pos1;
        acc1.createDate = new Date();

        Account acc2 = new Account();
        acc2.accountID = 2;
        acc2.email = "NguyenVanB@gmail.com";
        acc2.userName = "nguyenvanb";
        acc2.fullName = "Nguyễn Văn B";
        acc2.departmentID = null; // Cố tình để null
        acc2.PositionID = pos2;
        acc2.createDate = new Date();

        Account acc3 = new Account();
        acc3.accountID = 3;
        acc3.email = "NguyenVanC@gmail.com";
        acc3.userName = "nguyenvanc";
        acc3.fullName = "Nguyễn Văn C";
        acc3.departmentID = dep2;
        acc3.PositionID = pos2;
        acc3.createDate = new Date();

        Group group1 = new Group();
        group1.groupID = 1;
        group1.groupName = "Java Fresher";
        group1.accountID = acc1;
        group1.createDate = new Date();

        Group group2 = new Group();
        group2.groupID = 2;
        group2.groupName = "C# Fresher";
        group2.accountID = acc2;
        group2.createDate = new Date();

        GroupAccount ga1 = new GroupAccount();
        ga1.group = group1;
        ga1.account = acc1;
        ga1.joinDate = new Date();

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group1;
        ga2.account = acc2;
        ga2.joinDate = new Date();

        // Bổ sung liên kết để code IF/SWITCH bên dưới tính toán đúng
        acc2.groups = new Group[] { group1, group2 };
        group1.accounts = new Account[] { acc1, acc2 };

        // Khai báo mảng đúng 1 lần duy nhất!
        Account[] accounts = { acc1, acc2, acc3 };
        Department[] departments = { dep1, dep2, dep3 };
        Group[] groups = { group1, group2 };

        // Question 1: Kiểm tra Account thứ 2 bằng IF
        if (acc2.departmentID == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + acc2.departmentID.departmentName);
        }

        // Question 2: Kiểm tra số lượng Group của Account 2
        int countGroup = (acc2.groups == null) ? 0 : acc2.groups.length;
        if (countGroup == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (countGroup == 1 || countGroup == 2) {
            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
        } else if (countGroup == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else if (countGroup >= 4) {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

        // Question 3: Sử dụng toán tử Ternary cho Question 1
        System.out.println(acc2.departmentID == null ? "Nhân viên này chưa có phòng ban"
                : "Phòng ban của nhân viên này là " + acc2.departmentID.departmentName);

        // Question 4: Sử dụng toán tử Ternary kiểm tra Position Account 1
        System.out.println(acc1.PositionID.positionName == Position.PositionName.DEV
                ? "Đây là Developer"
                : "Người này không phải là Developer");

        // Question 5: Lấy ra số lượng account trong nhóm thứ 1
        int countAcc = (group1.accounts == null) ? 0 : group1.accounts.length;
        switch (countAcc) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
                break;
        }

        // Question 6: Dùng switch case làm lại Question 2
        switch (countGroup) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                break;
        }

        // Question 7: Dùng switch case làm lại Question 4
        switch (acc1.PositionID.positionName) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
                break;
        }

        // Question 8: In ra thông tin các account (Đã xử lý chống lỗi NullPointerException)
        System.out.println("--- Question 8 ---");
        for (Account acc : accounts) {
            String depName = (acc.departmentID != null) ? acc.departmentID.departmentName : "Chưa có";
            System.out.println("Email: " + acc.email + " - FullName: " + acc.fullName + " - Phòng ban: " + depName);
        }

        // Question 9: In ra thông tin các phòng ban (ID, Name)
        System.out.println("--- Question 9 ---");
        for (Department dep : departments) {
            System.out.println("ID: " + dep.departmentID + " - Name: " + dep.departmentName);
        }

        // Question 10: In ra thông tin account theo định dạng yêu cầu
        System.out.println("--- Question 10 ---");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + accounts[i].email);
            System.out.println("Full name: " + accounts[i].fullName);
            String depName = (accounts[i].departmentID != null) ? accounts[i].departmentID.departmentName : "Chưa có";
            System.out.println("Phòng ban: " + depName);
        }

        // Question 11: In ra thông tin department theo định dạng
        System.out.println("--- Question 11 ---");
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + departments[i].departmentID);
            System.out.println("Name: " + departments[i].departmentName);
        }

        // Question 12: Chỉ in ra thông tin 2 department đầu tiên
        System.out.println("--- Question 12 ---");
        for (int i = 0; i < 2; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + departments[i].departmentID);
            System.out.println("Name: " + departments[i].departmentName);
        }

        // Question 13: In ra thông tin tất cả các account ngoại trừ account thứ 2
        System.out.println("--- Question 13 ---");
        for (int i = 0; i < accounts.length; i++) {
            if (i == 1) {
                continue;
            }
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("Email: " + accounts[i].email);
            System.out.println("Full name: " + accounts[i].fullName);
            String depName = (accounts[i].departmentID != null) ? accounts[i].departmentID.departmentName : "Chưa có";
            System.out.println("Phòng ban: " + depName);
        }

        // Question 14: In ra thông tin tất cả các account có id < 4
        System.out.println("--- Question 14 ---");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].accountID < 4) {
                System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                System.out.println("Email: " + accounts[i].email);
                System.out.println("Full name: " + accounts[i].fullName);
                String depName = (accounts[i].departmentID != null) ? accounts[i].departmentID.departmentName : "Chưa có";
                System.out.println("Phòng ban: " + depName);
            }
        }

        // Question 15: In ra các số chẵn nhỏ hơn hoặc bằng 20
        System.out.println("--- Question 15 ---");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        // Question 16 - Làm lại Q10: In ra thông tin account bằng WHILE
        System.out.println("--- Question 16 (Q10 bằng WHILE) ---");
        int index = 0;
        while (index < accounts.length) {
            System.out.println("Thông tin account thứ " + (index + 1) + " là:");
            System.out.println("Email: " + accounts[index].email);
            System.out.println("Full name: " + accounts[index].fullName);
            String depName = (accounts[index].departmentID != null) ? accounts[index].departmentID.departmentName : "Chưa có";
            System.out.println("Phòng ban: " + depName);
            index++;
        }

        // Question 16 - Làm lại Q12: Chỉ in 2 department đầu tiên (Dùng lệnh BREAK)
        System.out.println("--- Question 16 (Q12 dùng BREAK) ---");
        int j = 0;
        while (j < departments.length) {
            if (j == 2) {
                break;
            }
            System.out.println("Thông tin department thứ " + (j + 1) + " là:");
            System.out.println("Id: " + departments[j].departmentID);
            System.out.println("Name: " + departments[j].departmentName);
            j++;
        }

        // Question 16 - Làm lại Q13: Bỏ qua account thứ 2 (Dùng lệnh CONTINUE)
        System.out.println("--- Question 16 (Q13 dùng CONTINUE) ---");
        int k = 0;
        while (k < accounts.length) {
            if (k == 1) {
                k++;
                continue;
            }
            System.out.println("Thông tin account thứ " + (k + 1) + " là:");
            System.out.println("Email: " + accounts[k].email);
            System.out.println("Full name: " + accounts[k].fullName);
            k++;
        }

        // Question 16 - Làm lại Q15: In số chẵn nhỏ hơn hoặc bằng 20
        System.out.println("--- Question 16 (Q15 in số chẵn) ---");
        int m = 1;
        while (m <= 20) {
            if (m % 2 == 0) {
                System.out.print(m + " ");
            }
            m++;
        }
        System.out.println();



        System.out.println("\n========= EXERCISE 2 =========");
        // Question 1: Khai báo 1 số nguyên = 5 và in ra
        System.out.println("--- Question 1 ---");
        int q1 = 5;
        System.out.printf("Số nguyên là: %d%n", q1);

        // Question 2: In số 100 000 000 với định dạng 100,000,000
        System.out.println("\n--- Question 2 ---");
        int q2 = 100000000;
        System.out.printf(java.util.Locale.US, "Số định dạng: %,d%n", q2);

        // Question 3: In số thực 5.567098 lấy 4 số thập phân
        System.out.println("\n--- Question 3 ---");
        float q3 = 5.567098f;
        System.out.printf("Số thực làm tròn: %.4f%n", q3);

        // Question 4: In chuỗi có chứa ngoặc kép
        System.out.println("\n--- Question 4 ---");
        String studentName = "Nguyễn Văn A";
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.%n", studentName);

        // Question 5: Lấy thời gian bây giờ và in ra theo format 24/04/2020 11h:16p:20s
        System.out.println("\n--- Question 5 ---");
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH'h':mm'p':ss's'");
        String currentTime = dateFormat.format(new Date());
        System.out.printf("Thời gian bây giờ là: %s%n", currentTime);

        // Question 6: In thông tin account theo dạng Bảng (Table)
        System.out.println("\n--- Question 6 ---");
        System.out.printf("%-25s | %-20s | %-15s%n", "EMAIL", "FULL NAME", "DEPARTMENT");
        System.out.println("--------------------------------------------------------------------");
        for (Account acc : accounts) {
            String depName = (acc.departmentID != null) ? acc.departmentID.departmentName : "Chưa có";
            System.out.printf("%-25s | %-20s | %-15s%n", acc.email, acc.fullName, depName);
        }

        CategoryQuestion cq1 = new CategoryQuestion();
        cq1.categoryID = 1;
        cq1.categoryName = "Java";

        Exam exam1 = new Exam();
        exam1.examID = 1;
        exam1.code = "VTI001";
        exam1.title = "Đề thi Java Basic";
        exam1.category = cq1;
        exam1.duration = 60;
        exam1.creator = acc1; // Sử dụng lại acc1 đã tạo ở trên
        exam1.createDate = new java.util.Date(); // Lấy ngày giờ hiện tại



        System.out.println("\n========= EXERCISE 3 =========");
        // Question 1: In ra thông tin Exam thứ 1 và format create date theo định dạng vietnamese (Ngày/Tháng/Năm)
        System.out.println("--- Question 1 ---");
        java.text.SimpleDateFormat viFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Exam 1 Code: " + exam1.code);
        System.out.println("Exam 1 Title: " + exam1.title);
        System.out.println("Ngày tạo (VN): " + viFormat.format(exam1.createDate));

        // Question 2: Format Năm – tháng – ngày – giờ – phút – giây
        System.out.println("\n--- Question 2 ---");
        java.text.SimpleDateFormat fullFormat = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        System.out.println("Exam đã tạo ngày: " + fullFormat.format(exam1.createDate));

        // Question 3: Chỉ in ra năm của create date
        System.out.println("\n--- Question 3 ---");
        java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy");
        System.out.println("Năm tạo Exam: " + yearFormat.format(exam1.createDate));

        // Question 4: Chỉ in ra tháng và năm
        System.out.println("\n--- Question 4 ---");
        java.text.SimpleDateFormat monthYearFormat = new java.text.SimpleDateFormat("MM-yyyy");
        System.out.println("Tháng-Năm tạo Exam: " + monthYearFormat.format(exam1.createDate));

        // Question 5: Chỉ in ra "MM-DD"
        System.out.println("\n--- Question 5 ---");
        java.text.SimpleDateFormat mmDdFormat = new java.text.SimpleDateFormat("MM-dd");
        System.out.println("Tháng-Ngày (MM-DD) tạo Exam: " + mmDdFormat.format(exam1.createDate));



        System.out.println("\n========= EXERCISE 4 =========");
        java.util.Random random = new java.util.Random();

        // Question 1: In ngẫu nhiên ra 1 số nguyên
        System.out.println("--- Question 1 ---");
        int randomInt = random.nextInt();
        System.out.println("Số nguyên ngẫu nhiên: " + randomInt);

        // Question 2: In ngẫu nhiên ra 1 số thực
        System.out.println("\n--- Question 2 ---");
        float randomFloat = random.nextFloat();
        System.out.println("Số thực ngẫu nhiên: " + randomFloat);

        // Question 3: Khai báo 1 array bao gồm các tên của các bạn trong lớp, in ngẫu nhiên 1 bạn
        System.out.println("\n--- Question 3 ---");
        String[] students = {"Lâm Vĩ Hoằng", "Nguyễn Văn A", "Trần Thị B", "Lê Văn C"};
        int randomIndex = random.nextInt(students.length);
        System.out.println("Tên ngẫu nhiên trong lớp: " + students[randomIndex]);

        // Question 4: Lấy ngẫu nhiên 1 ngày trong khoảng 24-07-1995 tới 20-12-1995
        System.out.println("\n--- Question 4 ---");
        long minDayQ4 = java.time.LocalDate.of(1995, 7, 24).toEpochDay();
        long maxDayQ4 = java.time.LocalDate.of(1995, 12, 20).toEpochDay();
        long randomDayQ4 = minDayQ4 + random.nextInt((int) (maxDayQ4 - minDayQ4) + 1);
        java.time.LocalDate randomDateQ4 = java.time.LocalDate.ofEpochDay(randomDayQ4);
        System.out.println("Ngày ngẫu nhiên (24/7/1995 - 20/12/1995): " + randomDateQ4);

        // Question 5: Lấy ngẫu nhiên 1 ngày trong khoảng 1 năm trở lại đây
        System.out.println("\n--- Question 5 ---");
        long today = java.time.LocalDate.now().toEpochDay(); // Ngày hôm nay
        long oneYearAgo = today - 365; // Cách đây 1 năm
        long randomDayQ5 = oneYearAgo + random.nextInt((int) (today - oneYearAgo) + 1);
        java.time.LocalDate randomDateQ5 = java.time.LocalDate.ofEpochDay(randomDayQ5);
        System.out.println("Ngày ngẫu nhiên trong 1 năm qua: " + randomDateQ5);

        // Question 6: Lấy ngẫu nhiên 1 ngày trong quá khứ
        System.out.println("\n--- Question 6 ---");
        long randomDayQ6 = random.nextInt((int) today);
        java.time.LocalDate randomDateQ6 = java.time.LocalDate.ofEpochDay(randomDayQ6);
        System.out.println("Ngày ngẫu nhiên trong quá khứ: " + randomDateQ6);

        // Question 7: Lấy ngẫu nhiên 1 số có 3 chữ số
        System.out.println("\n--- Question 7 ---");
        int random3Digits = random.nextInt(999 - 100 + 1) + 100;
        System.out.println("Số ngẫu nhiên có 3 chữ số: " + random3Digits);




        System.out.println("\n========= EXERCISE 5 =========");
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Question 1
        System.out.println("Nhập 3 số nguyên (cách nhau bởi dấu cách):");
        int a = scanner.nextInt(); int b = scanner.nextInt(); int c = scanner.nextInt();

        // Question 2
        System.out.println("Nhập 2 số thực (cách nhau bởi dấu cách):");
        float f1 = scanner.nextFloat(); float f2 = scanner.nextFloat();
        scanner.nextLine(); // clear buffer

        // Question 3
        System.out.println("Nhập họ và tên của bạn:");
        String inputName = scanner.nextLine();

        // Question 4
        System.out.println("Nhập ngày sinh (dd/MM/yyyy):");
        String dob = scanner.nextLine();

        // Question 7: Nhập số chẵn từ console
        System.out.println("\n--- Question 7 ---");
        int evenNum;
        while (true) {
            System.out.print("Mời bạn nhập vào 1 số chẵn: ");
            evenNum = scanner.nextInt();
            if (evenNum % 2 == 0) {
                System.out.println("Bạn đã nhập số chẵn: " + evenNum);
                break; // Hợp lệ thì thoát vòng lặp
            } else {
                System.out.println("Lỗi! Đây không phải số chẵn. Vui lòng nhập lại.");
            }
        }


        System.out.println("\n========= EXERCISE 6 =========");

        // Gọi hàm của Question 1
        System.out.println("--- Question 1 ---");
        printEvenNumbers();

        // Gọi hàm của Question 2 (Truyền mảng accounts đã tạo ở trên vào)
        System.out.println("\n--- Question 2 ---");
        printAccountsInfo(accounts);

        // Gọi hàm của Question 3
        System.out.println("\n--- Question 3 ---");
        printPositiveNumbers();


        // CHẠY TIẾP EX5
        // Question 8, 10, 11: Tạo Menu chức năng
        System.out.println("\n--- Question 8, 10, 11 (MENU) ---");
        while (true) {
            System.out.println("\n========= MENU CHỨC NĂNG =========");
            System.out.println("1. Tạo Account mới");
            System.out.println("2. Tạo Department mới");
            System.out.println("3. Thêm Group vào Account");
            System.out.println("4. Thêm Account vào Group ngẫu nhiên");
            System.out.print("Mời bạn nhập vào chức năng muốn sử dụng (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner); // Gọi hàm đã viết ở bên ngoài
                    break;
                case 2:
                    createDepartment(scanner);
                    break;
                case 3:
                    addGroupToAccount(scanner);
                    break;
                case 4:
                    addAccountToRandomGroup(scanner);
                    break;
                default:
                    System.out.println("Bạn nhập sai rồi! Mời bạn nhập lại.");
                    continue; // Quay lại đầu vòng lặp while (Bước 1 của Q8)
            }

            // Hỏi người dùng có muốn tiếp tục không (Question 10)
            System.out.print("\nBạn có muốn thực hiện chức năng khác không? (1: Có, 0: Không): ");
            int isContinue = scanner.nextInt();
            if (isContinue == 0) {
                System.out.println("Chương trình kết thúc. Hẹn gặp lại!");
                scanner.close();
                return;
            }
        }
    }

    // CÁC HÀM CỦA EXERCISE 5

    // Question 5: Method tạo Account
    public static void createAccount(java.util.Scanner scanner) {
        System.out.println("\n=== TẠO ACCOUNT ===");
        System.out.print("Nhập Position (1: Dev, 2: Test, 3: ScrumMaster, 4: PM): ");
        int posNum = scanner.nextInt();
        Position.PositionName posName = null;
        switch(posNum) {
            case 1: posName = Position.PositionName.DEV; break;
            case 2: posName = Position.PositionName.TEST; break;
            // Giả sử có thêm 2 enum này trong thực tế, ở đây mình set mặc định nếu nhập sai
            default: posName = Position.PositionName.DEV; break;
        }
        System.out.println("--> Đã tạo thành công Account với chức vụ: " + posName);
    }

    // Question 6: Method tạo Department
    public static void createDepartment(java.util.Scanner scanner) {
        scanner.nextLine(); // Dọn dẹp bộ nhớ đệm (trôi lệnh)
        System.out.println("\n=== TẠO DEPARTMENT ===");
        System.out.print("Nhập tên phòng ban mới: ");
        String depName = scanner.nextLine();
        System.out.println("--> Đã tạo thành công phòng ban: " + depName);
    }

    // Question 9: Method thêm Group vào Account
    public static void addGroupToAccount(java.util.Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n=== THÊM ACCOUNT VÀO GROUP ===");
        System.out.println("Danh sách username hiện có: nguyenvana, nguyenvanb, nguyenvanc");
        System.out.print("Nhập username bạn muốn chọn: ");
        String userName = scanner.nextLine();

        System.out.println("Danh sách group hiện có: Java Fresher, C# Fresher");
        System.out.print("Nhập tên group muốn thêm vào: ");
        String groupName = scanner.nextLine();

        System.out.println("--> Đã thêm account '" + userName + "' vào group '" + groupName + "'");
    }

    // Question 11: Method thêm Account vào Group ngẫu nhiên
    public static void addAccountToRandomGroup(java.util.Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n=== THÊM ACCOUNT VÀO GROUP NGẪU NHIÊN ===");
        System.out.println("Danh sách username hiện có: nguyenvana, nguyenvanb, nguyenvanc");
        System.out.print("Nhập username bạn muốn chọn: ");
        String userName = scanner.nextLine();

        String[] groups = {"Java Fresher", "C# Fresher", "Python Fresher"};
        int randomIndex = new java.util.Random().nextInt(groups.length);
        System.out.println("--> Đã thêm account '" + userName + "' vào group ngẫu nhiên là: " + groups[randomIndex]);
    }

    // CÁC HÀM CỦA EXERCISE 6

    // Question 1: Method in ra các số chẵn nguyên dương nhỏ hơn 10
    public static void printEvenNumbers() {
        System.out.print("Các số chẵn nhỏ hơn 10 là: ");
        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Question 2: Method in thông tin các account
    // Ta truyền mảng Account vào làm tham số (parameter) để hàm có dữ liệu xử lý
    public static void printAccountsInfo(Account[] accArray) {
        System.out.println("--- Thông tin danh sách Account ---");
        for (Account acc : accArray) {
            String depName = (acc.departmentID != null) ? acc.departmentID.departmentName : "Chưa có";
            System.out.printf("Email: %-20s | FullName: %-15s | Phòng ban: %s%n",
                    acc.email, acc.fullName, depName);
        }
    }

    // Question 3: Method in ra các số nguyên dương nhỏ hơn 10
    public static void printPositiveNumbers() {
        System.out.print("Các số nguyên dương nhỏ hơn 10 là: ");
        for (int i = 1; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}