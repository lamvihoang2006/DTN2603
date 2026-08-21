import java.util.Date;

public class Program {
    public static void main(String[] args) {
        // --- 1. DEPARTMENT ---
        Department dep1 = new Department();
        dep1.departmentID = 1;
        dep1.departmentName = "Sale";

        Department dep2 = new Department();
        dep2.departmentID = 2;
        dep2.departmentName = "Marketing";

        Department dep3 = new Department();
        dep3.departmentID = 3;
        dep3.departmentName = "Waiting Department";

        // --- 2. POSITION ---
        Position pos1 = new Position();
        pos1.positionID = 1;
        pos1.positionName = Position.PositionName.DEV;

        Position pos2 = new Position();
        pos2.positionID = 2;
        pos2.positionName = Position.PositionName.TEST;

        Position pos3 = new Position();
        pos3.positionID = 3;
        pos3.positionName = Position.PositionName.SCRUM_MASTER;

        // --- 3. ACCOUNT ---
        Account acc1 = new Account();
        acc1.accountID = 1;
        acc1.email = "hoang@gmail.com";
        acc1.userName = "hoanglam";
        acc1.fullName = "Lâm Vĩ Hoằng";
        acc1.departmentID = dep1;
        acc1.PositionID = pos1;
        acc1.createDate = new Date();

        Account acc2 = new Account();
        acc2.accountID = 2;
        acc2.email = "vana@gmail.com";
        acc2.userName = "vana";
        acc2.fullName = "Nguyễn Thị A";
        acc2.departmentID = dep2;
        acc2.PositionID = pos2;
        acc1.createDate = new Date();

        Account acc3 = new Account();
        acc3.accountID = 3;
        acc3.email = "vanb@gmail.com";
        acc3.userName = "vanb";
        acc3.fullName = "Trần Văn B";
        acc3.departmentID = dep3;
        acc3.PositionID = pos3;
        acc1.createDate = new Date();

        // --- 4. GROUP ---
        Group group1 = new Group();
        group1.groupID = 1;
        group1.groupName = "Java Fresher";
        group1.accountID = acc1;
        group1.createDate = new Date();

        Group group2 = new Group();
        group2.groupID = 2;
        group2.groupName = "C# Beginner";
        group2.accountID = acc2;
        group2.createDate = new Date();

        Group group3 = new Group();
        group3.groupID = 3;
        group3.groupName = "Front-end Master";
        group3.accountID = acc3;
        group3.createDate = new Date();

        // --- 5. GROUP ACCOUNT ---
        GroupAccount ga1 = new GroupAccount();
        ga1.group = group1;
        ga1.account = acc1;
        ga1.joinDate = new Date();

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group2;
        ga2.account = acc2;
        ga2.joinDate = new Date();

        GroupAccount ga3 = new GroupAccount();
        ga3.group = group3;
        ga3.account = acc3;
        ga3.joinDate = new Date();

        // --- 6. TYPE QUESTION ---
        TypeQuestion tq1 = new TypeQuestion();
        tq1.typeID = 1;
        tq1.typeName = TypeQuestion.TypeName.ESSAY;

        TypeQuestion tq2 = new TypeQuestion();
        tq2.typeID = 2;
        tq2.typeName = TypeQuestion.TypeName.MULTIPLE_CHOICE;

        TypeQuestion tq3 = new TypeQuestion();
        tq3.typeID = 3;
        tq3.typeName = TypeQuestion.TypeName.MULTIPLE_CHOICE;

        // --- 7. CATEGORY QUESTION ---
        CategoryQuestion cq1 = new CategoryQuestion();
        cq1.categoryID = 1;
        cq1.categoryName = "Java";

        CategoryQuestion cq2 = new CategoryQuestion();
        cq2.categoryID = 2;
        cq2.categoryName = "SQL";

        CategoryQuestion cq3 = new CategoryQuestion();
        cq3.categoryID = 3;
        cq3.categoryName = ".NET";

        // --- 8. QUESTION ---
        Question q1 = new Question();
        q1.questionID = 1;
        q1.content = "Java là gì?";
        q1.category = cq1;
        q1.type = tq1;
        q1.creator = acc1;
        q1.createDate = new Date();

        Question q2 = new Question();
        q2.questionID = 2;
        q2.content = "Câu lệnh JOIN trong SQL dùng để làm gì?";
        q2.category = cq2;
        q2.type = tq2;
        q2.creator = acc2;
        q2.createDate = new Date();

        Question q3 = new Question();
        q3.questionID = 3;
        q3.content = "OOP là viết tắt của từ gì?";
        q3.category = cq3;
        q3.type = tq2;
        q3.creator = acc3;
        q3.createDate = new Date();

        // --- 9. ANSWER ---
        Answer ans1 = new Answer();
        ans1.answerID = 1;
        ans1.content = "Java là ngôn ngữ lập trình hướng đối tượng.";
        ans1.question = q1;
        ans1.isCorrect = true;

        Answer ans2 = new Answer();
        ans2.answerID = 2;
        ans2.content = "JOIN dùng để kết hợp các bảng.";
        ans2.question = q2;
        ans2.isCorrect = true;

        Answer ans3 = new Answer();
        ans3.answerID = 3;
        ans3.content = "Object Oriented Programming";
        ans3.question = q3;
        ans3.isCorrect = true;

        // --- 10. EXAM ---
        Exam exam1 = new Exam();
        exam1.examID = 1;
        exam1.code = "JV_01";
        exam1.title = "Đề thi Java cơ bản";
        exam1.category = cq1;
        exam1.duration = 60;
        exam1.creator = acc1;
        exam1.createDate = new Date();

        Exam exam2 = new Exam();
        exam2.examID = 2;
        exam2.code = "SQL_01";
        exam2.title = "Đề thi SQL nâng cao";
        exam2.category = cq2;
        exam2.duration = 45;
        exam2.creator = acc2;
        exam2.createDate = new Date();

        Exam exam3 = new Exam();
        exam3.examID = 3;
        exam3.code = "NET_01";
        exam3.title = "Đề thi .NET cơ bản";
        exam3.category = cq3;
        exam3.duration = 90;
        exam3.creator = acc3;
        exam3.createDate = new Date();

        // --- 11. EXAM QUESTION ---
        ExamQuestion eq1 = new ExamQuestion();
        eq1.exam = exam1;
        eq1.question = q1;

        ExamQuestion eq2 = new ExamQuestion();
        eq2.exam = exam2;
        eq2.question = q2;

        ExamQuestion eq3 = new ExamQuestion();
        eq3.exam = exam3;
        eq3.question = q3;

        // QUESTION 3: IN ÍT NHẤT 1 GIÁ TRỊ CỦA MỖI ĐỐI TƯỢNG RA MÀN HÌNH
        System.out.println("--- THÔNG TIN KHỞI TẠO ---");
        System.out.println("Department 1 Name: " + dep1.departmentName);
        System.out.println("Position 1 Name: " + pos1.positionName);
        System.out.println("Account 1 FullName: " + acc1.fullName);
        System.out.println("Group 1 Name: " + group1.groupName);
        System.out.println("GroupAccount 1 Group Name: " + ga1.group.groupName);
        System.out.println("TypeQuestion 1 Name: " + tq1.typeName);
        System.out.println("CategoryQuestion 1 Name: " + cq1.categoryName);
        System.out.println("Question 1 Content: " + q1.content);
        System.out.println("Answer 1 Content: " + ans1.content);
        System.out.println("Exam 1 Title: " + exam1.title);
        System.out.println("ExamQuestion 1 Exam Code: " + eq1.exam.code);
    }
}