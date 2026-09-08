package frontend;

import backend.IQuanLySach;
import backend.QuanLySach;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        IQuanLySach quanLySach = new QuanLySach();
        while (true) {
            System.out.println("==== QUẢN LÝ THƯ VIỆN ====");
            System.out.println("1. Thêm mới tài liệu (Sách, Tạp chí, Báo).");
            System.out.println("2. Xoá tài liệu theo mã tài liệu.");
            System.out.println("3. Hiện thị thông tin về tài liệu.");
            System.out.println("4. Tìm kiếm tài liệu theo loại.");
            System.out.println("5. Thoát khỏi chương trình.");
            System.out.print("Mời bạn chọn chức năng: ");
            String choose = scanner.nextLine();

            switch (choose) {
                case "1":
                    quanLySach.themMoiTaiLieu();
                    break;
                case "2":
                    quanLySach.xoaTaiLieuTheoMa();
                    break;
                case "3":
                    quanLySach.hienThiThongTin();
                    break;
                case "4":
                    quanLySach.timKiemTheoLoai();
                    break;
                case "5":
                    System.out.println("Đã thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Nhập sai, vui lòng nhập lại!");
            }
        }
    }
}